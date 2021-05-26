package com.example.sorcerersguide.controller;

import com.example.sorcerersguide.model.Update;
import com.example.sorcerersguide.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dr")
public class NavigationController {

    @Autowired
    private UpdateService updateService;

    private final String prefixFolder = "/dr/";

    public static final Integer defaultPage = 1;
    public static final Integer defaultPageSize = 2;

    @GetMapping("/updates")
    public ModelAndView updates(@RequestParam Optional<String> query,
                                @RequestParam Optional<Integer> currentPage,
                                @RequestParam Optional<Integer> pageSize) {
        ModelAndView modelAndView = new ModelAndView();

        // Search update
        String heading = query.orElse("");
        String body = query.orElse("");
        Page<Update> updateList = updateService.getSearchedUpdates(heading, body,
                PageRequest.of(currentPage.orElse(defaultPage) - 1, pageSize.orElse(defaultPageSize)));
        Integer totalPages = updateList.getTotalPages();
        Long totalElements = updateList.getTotalElements();

        modelAndView.addObject("activePage", "updates");
        modelAndView.addObject("query", body);
        modelAndView.addObject("updates", updateList);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("totalElements", totalElements);
        modelAndView.setViewName(prefixFolder + "updates");
        return modelAndView;
    }

    @GetMapping("/queries")
    public ModelAndView queries() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activePage", "queries");
        modelAndView.setViewName(prefixFolder + "queries");
        return modelAndView;
    }

    @GetMapping("/faqs")
    public ModelAndView faqs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activePage", "faqs");
        modelAndView.setViewName(prefixFolder + "faqs");
        return modelAndView;
    }

}
