package com.dev.finalproject.service;

import com.dev.finalproject.domain.Employee;
import com.dev.finalproject.domain.EmployeeEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    public String createReport(List<EmployeeEvent> events, Employee employee) {
        StringBuilder report = new StringBuilder();
        int totalHours = 0;
        for (EmployeeEvent ee: events) {
            report.append("Date - ").append(ee.getEvent().getDate().toString()).append(", Event name - ").append(ee.getEvent().getName()).append(", hours - ").append(ee.getHours()).append(";\n");
            totalHours += ee.getHours();
        }
        report.append("Total hours - ").append(totalHours).append(";\n");
        double salary = totalHours * employee.getHourlyRate();
        report.append("Salary - ").append(salary).append(";\n");
        return report.toString();
    }
}
