package com.dev.finalproject.controller;


import com.dev.finalproject.domain.Position;
import com.dev.finalproject.service.jpa.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PositionController extends BaseSecurityController {
    @Autowired
    private PositionService service;

    @GetMapping("/admin/position")
    public ModelAndView getPositions(@RequestParam(required = false) String msg) {
        ModelAndView modelAndView = createModelAndView("admin/position/positions");
        modelAndView.addObject("positions", this.service.findAll());
        modelAndView.addObject("msg", msg);
        return modelAndView;
    }

    @GetMapping("/admin/position/delete")
    public String deletePosition(@RequestParam long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("msg", this.service.delete(id));
        return "redirect:/admin/position";
    }

    @GetMapping("/admin/position/edit")
    public ModelAndView editPosition(@RequestParam long id) {
        if (!this.service.exists(id)) {
            return this.createModelAndView("admin/position/positions");
        }
        ModelAndView modelAndView = this.createModelAndView("admin/position/edit");
        modelAndView.addObject("position", this.service.findById(id));
        return modelAndView;
    }
    @PostMapping("/admin/position/edit")
    public String handleEditPosition(@ModelAttribute Position position) {

        if (!this.service.exists(position.getId())) {
            return "redirect:/admin/position";
        }
        this.service.save(position);
        return "redirect:/admin/position";
    }

    @GetMapping("/admin/position/add")
    public ModelAndView addPosition() {
        ModelAndView modelAndView = this.createModelAndView("admin/position/add");
        modelAndView.addObject("position", new Position());

        return modelAndView;
    }
    @PostMapping("/admin/position/add")
    public String handleAddPosition(@ModelAttribute Position position) {
        this.service.save(position);
        return "redirect:/admin/position";
    }

}
