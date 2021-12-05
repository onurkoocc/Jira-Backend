package com.kafein.jirabackend.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.kafein.jirabackend.model.ERole;
import com.kafein.jirabackend.model.Role;
import com.kafein.jirabackend.model.User;
import com.kafein.jirabackend.payload.request.LoginRequest;
import com.kafein.jirabackend.payload.request.SignupRequest;
import com.kafein.jirabackend.payload.response.JwtResponse;
import com.kafein.jirabackend.payload.response.MessageResponse;
import com.kafein.jirabackend.repository.RoleRepository;
import com.kafein.jirabackend.repository.UserRepository;
import com.kafein.jirabackend.security.jwt.JwtUtils;
import com.kafein.jirabackend.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String strRole = signUpRequest.getRole();
        Role role = new Role();
        switch (strRole) {
            case "ROLE_ADMIN" -> {
                Role ROLE_ADMIN = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                role.setRole(ROLE_ADMIN);
            }
            case "ROLE_DEVELOPER" -> {
                Role ROLE_DEVELOPER = roleRepository.findByName(ERole.ROLE_DEVELOPER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                role.setRole(ROLE_DEVELOPER);
            }
            case "ROLE_TESTER" -> {
                Role ROLE_TESTER = roleRepository.findByName(ERole.ROLE_TESTER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                role.setRole(ROLE_TESTER);
            }
            case "ROLE_OPERATION" -> {
                Role ROLE_OPERATION = roleRepository.findByName(ERole.ROLE_OPERATION)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                role.setRole(ROLE_OPERATION);
            }
        }


        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}