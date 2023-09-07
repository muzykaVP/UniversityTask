package com.example.universitytask.handlers;

import com.example.universitytask.bot.RegexCommandContainer;
import com.example.universitytask.model.Department;
import com.example.universitytask.model.Lector;
import com.example.universitytask.service.DepartmentService;
import com.example.universitytask.service.LectorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class ResponseHandler {
    private final DepartmentService departmentService;
    private final LectorService lectorService;
    @SneakyThrows
    public String handleResponse(String request) {
        String lowerCaseRequest = request.toLowerCase();
        if (lowerCaseRequest.matches(RegexCommandContainer.START.value)) {
            return "Hello! \nIt's University bot for BotsCrew test task. \nMade by Vladyslav Muzyka \nPlease sending /help command to receive list of available commands";
        }
        if (lowerCaseRequest.matches(RegexCommandContainer.HELP.value)) {
            return Files.readString(Path.of("src/main/resources/init/HelpCommand"));
        }
        if (lowerCaseRequest.matches(RegexCommandContainer.SHOW_DEPARTMENTS.value)) {
            List<String> departmentNames = departmentService.getAll().stream().map(Department::getName).toList();
            return String.join(", ", departmentNames);
        }
        if (lowerCaseRequest.matches(RegexCommandContainer.SHOW_EMPLOYEES.value)) {
            List<String> lectorNames = lectorService.getAll().stream().map(Lector::getName).toList();
            return String.join(", ", lectorNames);
        }
        if (lowerCaseRequest.matches(RegexCommandContainer.SHOW_STATISTICS.value)) {
            Pattern pattern = Pattern.compile(RegexCommandContainer.SHOW_STATISTICS.value);
            Matcher matcher = pattern.matcher(lowerCaseRequest);
            if (matcher.find()) {
                String departmentName = matcher.group(1);
                return departmentService.getDepartmentStatisticsByName(departmentName);
            }
        }
        if (lowerCaseRequest.matches(RegexCommandContainer.DEPARTMENT_HEAD.value)) {
            Pattern pattern = Pattern.compile(RegexCommandContainer.DEPARTMENT_HEAD.value);
            Matcher matcher = pattern.matcher(lowerCaseRequest);
            if (matcher.find()) {
                String departmentName = matcher.group(1);
                return departmentService.getDepartmentHead(departmentName);
            }
        }
        if (lowerCaseRequest.matches(RegexCommandContainer.AVERAGE_DEPARTMENT_SALARY.value)) {
            Pattern pattern = Pattern.compile(RegexCommandContainer.AVERAGE_DEPARTMENT_SALARY.value);
            Matcher matcher = pattern.matcher(lowerCaseRequest);
            if (matcher.find()) {
                String departmentName = matcher.group(1);
                return departmentService.getAverageSalary(departmentName);
            }
        }
        if (lowerCaseRequest.matches(RegexCommandContainer.DEPARTMENT_EMPLOYEES_COUNT.value)) {
            Pattern pattern = Pattern.compile(RegexCommandContainer.DEPARTMENT_EMPLOYEES_COUNT.value);
            Matcher matcher = pattern.matcher(lowerCaseRequest);
            if (matcher.find()) {
                String departmentName = matcher.group(1);
                return departmentService.getEmployeesCount(departmentName);
            }
        }
        if (lowerCaseRequest.matches(RegexCommandContainer.GLOBAL_SEARCH_BY_TEMPLATE.value)) {
            Pattern pattern = Pattern.compile(RegexCommandContainer.GLOBAL_SEARCH_BY_TEMPLATE.value);
            Matcher matcher = pattern.matcher(lowerCaseRequest);
            if (matcher.find()) {
                String departmentName = matcher.group(1);
                List<String> lectorNames = lectorService.getAllByNameContains(departmentName).stream().map(Lector::getName).toList();
                return String.join(", ", lectorNames);
            }
        }
        return "Unknown command";
    }

}
