package com.dev.finalproject.service;

import com.dev.finalproject.domain.Employee;
import com.dev.finalproject.service.jpa.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class SecurityService {
    @Autowired
    private EmployeeService employeeService;
    public ModelAndView createModelAndView(String viewName) {
        ModelAndView result = new ModelAndView(viewName);
        result.addObject("curUser", getUser());

        return result;
    }

    public Employee getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object credential = auth.getPrincipal();
        Employee employee;
        if (credential.equals("anonymousUser")) {
            employee = null;
        } else {
            employee =this.employeeService.findByEmail(((User) credential).getUsername());

        }
        return employee;
    }

}
