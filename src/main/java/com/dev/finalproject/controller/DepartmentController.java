package com.dev.finalproject.controller;

import com.dev.finalproject.domain.Department;
import com.dev.finalproject.service.jpa.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DepartmentController extends BaseSecurityController{
    @Autowired
    private DepartmentService service;

    @GetMapping("/admin/department")
    public ModelAndView getDepartments(@RequestParam(required = false) String msg) {
        ModelAndView modelAndView = createModelAndView("admin/department/departments");
        modelAndView.addObject("departments", this.service.findAll());
        modelAndView.addObject("msg", msg);
        return modelAndView;
    }

    @GetMapping("/admin/department/delete")
    public String deleteDepartment(@RequestParam long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("msg", this.service.delete(id));
        return "redirect:/admin/department";
    }

    @GetMapping("/admin/department/edit")
    public ModelAndView editDepartment(@RequestParam long id) {
        if (!this.service.exists(id)) {
            return this.createModelAndView("admin/department/departments");
        }
        ModelAndView modelAndView = this.createModelAndView("admin/department/edit");
        modelAndView.addObject("department", this.service.findById(id));
        return modelAndView;
    }
    @PostMapping("/admin/department/edit")
    public String handleEditDepartment(@ModelAttribute Department department) {

        if (!this.service.exists(department.getId())) {
            return "redirect:/admin/department";
        }
        this.service.save(department);
        return "redirect:/admin/department";
    }

    @GetMapping("/admin/department/add")
    public ModelAndView addDepartment() {
        ModelAndView modelAndView = this.createModelAndView("admin/department/add");
        modelAndView.addObject("department", new Department());

        return modelAndView;
    }
    @PostMapping("/admin/department/add")
    public String handleAddDepartment(@ModelAttribute Department department) {
        this.service.save(department);
        return "redirect:/admin/department";
    }

}
