package com.example.universitytask.service;

import com.example.universitytask.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    Department save(Department department);
    List<Department> saveAll(Iterable<Department> departments);
    Department findByName(String departmentName);
    String getAverageSalary(String departmentName);
    String getEmployeesCount(String departmentName);
    String getDepartmentStatisticsByName(String departmentName);
    String getDepartmentHead(String departmentName);
    List<Department> getAll();
}
