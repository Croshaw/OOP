package me.croshaw.lab7.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "employees", schema = "public")
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "employee_surname", nullable = false)
    private String surname;
    @Column(name = "employee_name", nullable = false)
    private String name;
    @Column(name = "employee_patronymic", nullable = false)
    private String patronymic;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_role_id",
    referencedColumnName = "role_id", nullable = false)
    private Role employeeRole;
    public Employee() { }
    public Employee(String surname, String name, String patronymic, Role employeeRole) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.employeeRole = employeeRole;
    }
    public Integer getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Role getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(Role employeeRole) {
        this.employeeRole = employeeRole;
    }
}
