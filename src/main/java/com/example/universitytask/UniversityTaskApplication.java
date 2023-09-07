package com.example.universitytask;

import com.example.universitytask.util.InitDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class UniversityTaskApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UniversityTaskApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        if (Boolean.parseBoolean(environment.getProperty("init.data.enabled"))) {
            InitDataService initDataService = context.getBean(InitDataService.class);
            initDataService.initMockData();
        }
    }
}
