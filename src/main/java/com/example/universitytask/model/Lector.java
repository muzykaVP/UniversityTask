package com.example.universitytask.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String degree;

    @ManyToMany
    @JoinTable(name = "lectors_departments",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> departments;

    private BigDecimal salary;
}
