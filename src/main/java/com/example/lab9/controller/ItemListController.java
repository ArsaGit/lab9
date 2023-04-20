package com.example.lab9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemListController {
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
}
