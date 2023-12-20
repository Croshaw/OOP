package me.croshaw.lab7.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles", schema = "public")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_name", nullable = false, unique = true)
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "employeeRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;
    public Role() { }
    public Role(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
