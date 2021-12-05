package com.kafein.jirabackend.model;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EStatus name;

    public Status() {
    }

    public Status(Integer id, EStatus name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EStatus getName() {
        return name;
    }

    public void setName(EStatus name) {
        this.name = name;
    }
}
