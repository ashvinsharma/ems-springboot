package com.ems.springboot.model;


import javax.persistence.*;
import java.util.LinkedHashMap;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "designation_id", referencedColumnName = "id")
    private Designation designationId;

    @Column(name = "contact", nullable = false, unique = true, length = 10)
    private String contact;

    public Employee() {

    }

    public String toString() {
        return String.format("[id: %d, email: %s, name: %s, designation: [id: %d, name: %s], contact: %s]",
                getId(), getEmail(), getName(), getDesignationId().getId(), getDesignationId().getName(), getContact());
    }

    public Employee(String email, String name, Designation designationId, String contact) {
        this.email = email;
        this.name = name;
        this.designationId = designationId;
        this.contact = contact;
    }

    public LinkedHashMap toLinkedHashMap() {
        LinkedHashMap<String, ? super Object> m = new LinkedHashMap<>();
        m.put("id", getId());
        m.put("name", getName());
        m.put("email", getEmail());
        m.put("designation", getDesignationId());
        m.put("contact", getContact());
        return m;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Designation getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Designation designationId) {
        this.designationId = designationId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
