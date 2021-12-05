package com.kafein.jirabackend.model;

import javax.persistence.*;

@Entity
@Table(name = "priority")
public class Priority {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EPriority name;

    public Priority() {
    }

    public Priority(Integer id, EPriority name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EPriority getName() {
        return name;
    }

    public void setName(EPriority name) {
        this.name = name;
    }
}
