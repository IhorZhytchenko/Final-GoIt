package com.dev.finalproject.controller;

import com.dev.finalproject.domain.Employee;
import com.dev.finalproject.service.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class EmployeeController extends BaseSecurityController {
    @Autowired
    private EmployeeService service;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StateService stateService;

    @GetMapping("/admin/employee")
    public ModelAndView getEmployees() {
        ModelAndView modelAndView = createModelAndView("admin/employee/employees");
        modelAndView.addObject("employees", this.service.findAll());
        return modelAndView;
    }

    @GetMapping("/admin/employee/delete")
    public String deleteEmployee(@RequestParam long id) {
         this.service.delete(id);
        return "redirect:/admin/employee";
    }

    @GetMapping("/admin/employee/edit")
    public ModelAndView editEmployee(@RequestParam long id) {
        if (!this.service.exists(id)) {
            return this.createModelAndView("admin/employee/employees");
        }
        ModelAndView modelAndView = this.createModelAndView("admin/employee/edit");
        modelAndView.addObject("employee", this.service.findById(id));
        modelAndView.addObject("departments", this.departmentService.findAll());
        modelAndView.addObject("positions", this.positionService.findAll());
        modelAndView.addObject("states", this.stateService.findAll());
        modelAndView.addObject("roless", this.roleService.findAll());
        return modelAndView;
    }

    @PostMapping("/admin/employee/edit")
    public String handleEditEmployee(@ModelAttribute Employee employee, @RequestParam String pass) {
        if (pass != null && pass.trim().length() > 0) {
            employee.setPassword(this.service.encode(pass));
        }

        if (!this.service.exists(employee.getId())) {
            return "redirect:/admin/employee";
        }
        this.service.update(employee);
        return "redirect:/admin/employee";
    }

    @GetMapping("/admin/employee/add")
    public ModelAndView addEmployee() {
        ModelAndView modelAndView = this.createModelAndView("admin/employee/add");
        modelAndView.addObject("employee", new Employee());
        modelAndView.addObject("departments", this.departmentService.findAll());
        modelAndView.addObject("positions", this.positionService.findAll());
        modelAndView.addObject("states", this.stateService.findAll());
        modelAndView.addObject("roless", this.roleService.findAll());
        return modelAndView;
    }
    @PostMapping("/admin/employee/add")
    public String handleAddEmployee(@ModelAttribute Employee employee) {
        this.service.save(employee);
        return "redirect:/admin/employee";
    }

    @GetMapping("/admin/employee/hours/info")
    public ModelAndView getEmployeeHours(@RequestParam long id) {
        ModelAndView modelAndView = createModelAndView("admin/employee/hoursInfo");
        modelAndView.addObject("employee", this.service.findById(id));
        return modelAndView;
    }

}
