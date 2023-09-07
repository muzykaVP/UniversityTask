package com.example.universitytask.service.impl;

import com.example.universitytask.model.Department;
import com.example.universitytask.model.Lector;
import com.example.universitytask.repository.DepartmentRepository;
import com.example.universitytask.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> saveAll(Iterable<Department> departments) {
        return departmentRepository.saveAll(departments);
    }

    @Override
    public Department findByName(String departmentName) {
        return departmentRepository.findByName(departmentName).orElseThrow(
                () -> new NoSuchElementException("Department with name: " + departmentName + ", not found!"));
    }

    @Override
    public String getAverageSalary(String departmentName) {
        try {
            Department department = findByName(departmentName);
            List<Lector> lectors = department.getLectors();
            Optional<BigDecimal> salarySum = lectors.stream().map(Lector::getSalary).reduce(BigDecimal::add);
            return salarySum.isPresent()
                    ? String.format("The average salary of %s is %s", department.getName(), salarySum.get().divide(BigDecimal.valueOf(lectors.size())))
                    : "There are no employees in department: " + department.getName();
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
    }

    @Override
    public String getEmployeesCount(String departmentName) {
        try {
            Department department = findByName(departmentName);
            return String.valueOf(department.getLectors().size());
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
    }

    @Override
    public String getDepartmentHead(String departmentName) {
        try {
            Department department = findByName(departmentName);
            return String.format("Head of %s department is %s", department.getName(), department.getHead().getName());
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
    }

    @Override
    public String getDepartmentStatisticsByName(String departmentName) {
        Map<String, Long> statistics = departmentRepository.getDepartmentStatisticsByName(departmentName).stream()
                .collect(Collectors.toMap(result -> (String) result[0], result -> (Long) result[1]));
        if (statistics.isEmpty()) {
            return "Couldn't find statistics for department with name: " + departmentName;
        }
        StringBuilder stringBuilder = new StringBuilder();
        statistics.forEach((key, value) -> stringBuilder.append(key).append(" - ").append(value).append(System.lineSeparator()));
        return stringBuilder.toString().strip();
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
}
