package com.users.users.controller;


import com.users.users.dto.Search;
import com.users.users.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/fingpost")
    public String findPost(@ModelAttribute("request") Search search, Model model){

        if(search.getName().isEmpty()) return "redirect:/";
        model.addAttribute("searchlist", searchService.search(search.getName()));
        return "site/index";
    }
}
