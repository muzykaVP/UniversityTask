package com.example.universitytask.util;

import com.example.universitytask.model.Department;
import com.example.universitytask.model.Lector;
import com.example.universitytask.service.DepartmentService;
import com.example.universitytask.service.LectorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitDataService {
    private final LectorService lectorService;
    private final DepartmentService departmentService;

    public void initMockData() {
        List<Lector> lectors = readResourceFromFilePath("init/LectorsInitData.json", Lector.class);
        List<Department> departments = readResourceFromFilePath("init/DepartmentInitData.json", Department.class);
        departmentService.saveAll(departments);
        lectorService.saveAll(lectors);
        List<Department> allDepartments = departmentService.getAll();
        int lectorsListSize = lectors.size();
        for (Department dep : allDepartments) {
            dep.setHead(lectors.get(--lectorsListSize));
        }
        departmentService.saveAll(allDepartments);
    }

    private <T> List<T> readResourceFromFilePath(String path, Class<T> type) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Resource resource = new ClassPathResource(path);
            if (resource.exists()) {
                try (InputStream inputStream = resource.getInputStream()) {
                    CollectionType listType =  objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, type);
                    return objectMapper.readValue(inputStream, listType);
                }
            } else {
                System.err.println("File not found: " + path);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return Collections.emptyList();
    }
}
