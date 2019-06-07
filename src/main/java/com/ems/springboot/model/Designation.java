package com.ems.springboot.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Transactional(readOnly = true)
@Table(name = "designation")
public class Designation {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public Designation() {
    }

    public Designation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[id: %d, name: %s]", getId(), getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
