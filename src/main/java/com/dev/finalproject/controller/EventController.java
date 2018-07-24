package com.dev.finalproject.controller;

import com.dev.finalproject.domain.Employee;
import com.dev.finalproject.domain.EmployeeEvent;
import com.dev.finalproject.domain.Event;
import com.dev.finalproject.service.ReportService;
import com.dev.finalproject.service.jpa.EmployeeService;
import com.dev.finalproject.service.jpa.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class EventController extends BaseSecurityController {
    @Autowired
    private EventService service;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ReportService reportService;


    @GetMapping("/admin/event")
    public ModelAndView getEvents() {
        ModelAndView modelAndView = createModelAndView("admin/event/events");
        modelAndView.addObject("events", this.service.findAll());
        return modelAndView;
    }
    @GetMapping("/admin/event/info")
    public ModelAndView getEventInfo(@RequestParam long id) {
        ModelAndView modelAndView = createModelAndView("admin/event/info");
        modelAndView.addObject("event", this.service.findById(id));
        return modelAndView;
    }
    @GetMapping("/admin/employee/hours/data")
    public @ResponseBody String getHoursInfo(@RequestParam long employeeId, @RequestParam String startDate, @RequestParam String finishDate) {
        List<EmployeeEvent> events = this.service.getEmployeeEventsByPeriodAndEmployee(employeeId, startDate, finishDate);
        Employee employee = this.employeeService.findById(employeeId);
        return this.reportService.createReport(events, employee);
    }



    @GetMapping("/admin/event/add")
    public ModelAndView addEvent() {
        ModelAndView modelAndView = this.createModelAndView("admin/event/add");
        modelAndView.addObject("event", new Event());
        modelAndView.addObject("employees", this.employeeService.findWorking());
        return modelAndView;
    }
    @PostMapping("/admin/event/add")
    public ModelAndView handleAddEvent(@ModelAttribute Event event,
                                      @RequestParam(value = "employeesId") Long[] employees) {
        ModelAndView modelAndView = this.createModelAndView("admin/event/addNext");
        Event ev = this.service.getIntermediateEvent(event, employees);
        modelAndView.addObject("event", ev);
        return modelAndView;
    }
    @PostMapping("/admin/event/addfinal")
    public String handleAddEventFinal(@ModelAttribute Event event) {
        this.service.save(event);
        return "redirect:/admin/event";
    }
}
