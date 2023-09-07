package com.example.universitytask.repository;

import com.example.universitytask.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Query("SELECT l FROM Lector l WHERE LOWER(l.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Lector> findByNameLike(@Param("name")String name);
    Optional<Lector> findByName(String name);
}
