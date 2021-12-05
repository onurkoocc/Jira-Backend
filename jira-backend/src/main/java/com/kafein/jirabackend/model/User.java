package com.kafein.jirabackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "username"),@UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id=0;
    private String name = "";
    private String surname = "";
    private String loginlog = "";

    @NotBlank
    @Size(max = 50)
    @Email
    private String email = "";

    @NotBlank
    @Size(max = 20)
    private String username = "";

    @NotBlank
    @Size(max = 120)
    private String password = "";

    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;

    public User() {
    }


    public User(String username,String email,  String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String name, String surname, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLoginlog() {
        return loginlog;
    }

    public void setLoginlog(String loginlog) {
        this.loginlog = loginlog;
    }
}
