package com.dev.finalproject.controller;

import com.dev.finalproject.domain.Employee;
import com.dev.finalproject.service.jpa.EmployeeService;
import com.dev.finalproject.service.jpa.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleController extends BaseSecurityController {
    @Autowired
    private EmployeeService service;
    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public ModelAndView getEmployees() {
        ModelAndView modelAndView = createModelAndView("role/index");
        modelAndView.addObject("employees", this.service.findAll());
        return modelAndView;
    }

    @GetMapping("/role/edit")
    public ModelAndView editEmployee(@RequestParam long id) {
        if (!this.service.exists(id)) {
            return this.createModelAndView("role/index");
        }
        ModelAndView modelAndView = this.createModelAndView("role/edit");
        modelAndView.addObject("employee", this.service.findById(id));
        modelAndView.addObject("roless", this.roleService.findAll());
        return modelAndView;
    }
    @PostMapping("/role/edit")
    public String handleEditEmployee(@ModelAttribute Employee employee) {

        if (!this.service.exists(employee.getId())) {
            return "redirect:/role";
        }
        this.service.updateRole(employee);
        return "redirect:/role";
    }
}
