package com.example.universitytask.repository;

import com.example.universitytask.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.lectors LEFT JOIN FETCH d.head WHERE LOWER(d.name) = LOWER(:name)")
    Optional<Department> findByName(@Param("name")String departmentName);

    @Query("SELECT l.degree, COUNT(l.degree) FROM Department d LEFT JOIN d.lectors l WHERE LOWER(d.name) = LOWER(:name) GROUP BY l.degree")
    List<Object[]> getDepartmentStatisticsByName(@Param("name")String departmentName);

    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.lectors l LEFT JOIN FETCH d.head")
    List<Department> findAll();
    void deleteByName(String departmentName);
}
