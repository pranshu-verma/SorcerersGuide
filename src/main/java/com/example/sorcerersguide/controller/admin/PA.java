package com.example.sorcerersguide.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/pa")
public class PA {

    private final String prefixFolder = "/admin/pa/";

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activeProgram", "pa");
        modelAndView.addObject("activePage", "admin");
        modelAndView.setViewName(prefixFolder + "index");
        return modelAndView;
    }

    @GetMapping("/updates")
    public ModelAndView updates() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activeProgram", "pa");
        modelAndView.addObject("activePage", "admin");
        modelAndView.setViewName(prefixFolder + "updates");
        return modelAndView;
    }

    @GetMapping("/queries")
    public ModelAndView queries() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activeProgram", "pa");
        modelAndView.addObject("activePage", "admin");
        modelAndView.setViewName(prefixFolder + "queries");
        return modelAndView;
    }

    @GetMapping("/faqs")
    public ModelAndView faqs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activeProgram", "pa");
        modelAndView.addObject("activePage", "admin");
        modelAndView.setViewName(prefixFolder + "faqs");
        return modelAndView;
    }

    @GetMapping("/allocations")
    public ModelAndView allocations() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activeProgram", "pa");
        modelAndView.addObject("activePage", "admin");
        modelAndView.setViewName(prefixFolder + "allocations");
        return modelAndView;
    }

}
