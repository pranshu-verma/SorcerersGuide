package com.example.sorcerersguide.controller;

import com.example.sorcerersguide.model.Faq;
import com.example.sorcerersguide.model.Query;
import com.example.sorcerersguide.model.Update;
import com.example.sorcerersguide.service.FaqService;
import com.example.sorcerersguide.service.QueryService;
import com.example.sorcerersguide.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/rp")
public class RPController {

    @Autowired
    private UpdateService updateService;

    @Autowired
    private QueryService queryService;

    @Autowired
    private FaqService faqService;

    private final String prefixFolder = "rp/";

    public static final Integer defaultPage = 1;
    public static final Integer defaultPageSize = 30;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activeProgram", "rp");
        modelAndView.setViewName(prefixFolder + "index");
        return modelAndView;
    }

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
        modelAndView.addObject("activeProgram", "rp");
        modelAndView.addObject("query", body);
        modelAndView.addObject("updates", updateList);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("currentPage", currentPage.orElse(defaultPage));
        modelAndView.addObject("totalElements", totalElements);
        modelAndView.setViewName(prefixFolder + "updates");
        return modelAndView;
    }

    @GetMapping("/checklist")
    public ModelAndView checklist() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activePage", "checklist");
        modelAndView.addObject("activeProgram", "rp");
        modelAndView.setViewName(prefixFolder + "checklist");
        return modelAndView;
    }

    @GetMapping("/queries")
    public ModelAndView queries(@RequestParam Optional<String> query,
                                @RequestParam Optional<Integer> currentPage,
                                @RequestParam Optional<Integer> pageSize) {
        ModelAndView modelAndView = new ModelAndView();

        // Search query
        String heading = query.orElse("");
        String body = query.orElse("");
        Page<Query> queryList = queryService.getSearchedQueries(heading, body,
                PageRequest.of(currentPage.orElse(defaultPage) - 1, pageSize.orElse(defaultPageSize)));
        Integer totalPages = queryList.getTotalPages();
        Long totalElements = queryList.getTotalElements();

        modelAndView.addObject("activePage", "queries");
        modelAndView.addObject("activeProgram", "rp");
        modelAndView.addObject("query", body);
        modelAndView.addObject("queries", queryList);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("currentPage", currentPage.orElse(defaultPage));
        modelAndView.addObject("totalElements", totalElements);
        modelAndView.setViewName(prefixFolder + "queries");
        return modelAndView;
    }

    @GetMapping("/faqs")
    public ModelAndView faqs(@RequestParam Optional<String> query,
                                @RequestParam Optional<Integer> currentPage,
                                @RequestParam Optional<Integer> pageSize) {
        ModelAndView modelAndView = new ModelAndView();

        // Search FAQ
        String question = query.orElse("");
        String answer = query.orElse("");
        Page<Faq> faqList = faqService.getSearchedFaqs(question, answer,
                PageRequest.of(currentPage.orElse(defaultPage) - 1, pageSize.orElse(defaultPageSize)));
        Integer totalPages = faqList.getTotalPages();
        Long totalElements = faqList.getTotalElements();

        modelAndView.addObject("activePage", "faqs");
        modelAndView.addObject("activeProgram", "rp");
        modelAndView.addObject("query", question);
        modelAndView.addObject("faqs", faqList);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("currentPage", currentPage.orElse(defaultPage));
        modelAndView.addObject("totalElements", totalElements);
        modelAndView.setViewName(prefixFolder + "faqs");
        return modelAndView;
    }

    @GetMapping("/program-updates")
    public ModelAndView programUpdates(@RequestParam Optional<String> query,
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

        modelAndView.addObject("activePage", "program-updates");
        modelAndView.addObject("activeProgram", "rp");
        modelAndView.addObject("query", body);
        modelAndView.addObject("updates", updateList);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("currentPage", currentPage.orElse(defaultPage));
        modelAndView.addObject("totalElements", totalElements);
        modelAndView.setViewName(prefixFolder + "program-updates");
        return modelAndView;
    }

}
