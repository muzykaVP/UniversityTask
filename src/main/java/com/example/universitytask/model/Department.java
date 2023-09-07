package com.example.universitytask.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_id", referencedColumnName = "id")
    private Lector head;

    @ManyToMany(mappedBy = "departments")
    private List<Lector> lectors;
}
