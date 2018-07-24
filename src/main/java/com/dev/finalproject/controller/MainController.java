package com.dev.finalproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class MainController extends BaseSecurityController {
    @GetMapping("/")
    public ModelAndView index() {
        return createModelAndView("index");
    }

    @GetMapping("/admin")
    public ModelAndView indexAdmin() {
        return createModelAndView("admin/index");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/myinfo")
    public ModelAndView myPage() {
        return createModelAndView("myPage");
    }

}
