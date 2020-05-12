package com.voting.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VotingController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
