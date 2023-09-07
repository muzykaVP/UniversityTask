package com.example.universitytask.service;

import com.example.universitytask.model.Lector;

import java.util.List;

public interface LectorService {
    Lector save(Lector lector);
    List<Lector> saveAll(List<Lector> lectors);
    Lector getByName(String name);
    List<Lector> getAllByNameContains(String name);
    List<Lector> getAll();
}
