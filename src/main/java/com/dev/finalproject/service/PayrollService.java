package com.dev.finalproject.service;

import com.dev.finalproject.domain.Employee;
import com.dev.finalproject.domain.EmployeeEvent;
import com.dev.finalproject.service.jpa.EmployeeService;
import com.dev.finalproject.service.jpa.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private MSender mSender;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void sendPayroll() {
        List<Employee> employees = this.employeeService.findAll();
        String startDate = LocalDate.now().minusMonths(1).toString();
        String finishDate = LocalDate.now().minusDays(1).toString();
        for (Employee emp: employees) {
            List<EmployeeEvent> events = this.eventService.getEmployeeEventsByPeriodAndEmployee(emp.getId(), startDate, finishDate);
            String report = this.reportService.createReport(events, emp);
            try {
                this.mSender.send(emp.getEmail(), "Payroll", report);
            } catch (MailException e) {
                e.printStackTrace();
            }

        }
    }
}
