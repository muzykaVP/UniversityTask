package com.example.universitytask.service.impl;

import com.example.universitytask.model.Lector;
import com.example.universitytask.repository.LectorRepository;
import com.example.universitytask.service.LectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LectorServiceImpl implements LectorService {
    private final LectorRepository lectorRepository;

    @Override
    public Lector save(Lector lector) {
        return lectorRepository.save(lector);
    }

    @Override
    public List<Lector> saveAll(List<Lector> lectors) {
        return lectorRepository.saveAll(lectors);
    }

    @Override
    public Lector getByName(String name) {
        return lectorRepository.findByName(name).orElseThrow(
                () -> new NoSuchElementException("Lector with name: " + name + ", not found!"));
    }

    @Override
    public List<Lector> getAllByNameContains(String name) {
        return lectorRepository.findByNameLike(name);
    }

    @Override
    public List<Lector> getAll() {
        return lectorRepository.findAll();
    }
}
