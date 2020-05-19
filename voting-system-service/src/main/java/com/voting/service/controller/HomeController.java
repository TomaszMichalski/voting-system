package com.voting.service.controller;

import com.voting.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/test")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity adminTest() {
        return new ResponseEntity(new ApiResponse(true, "You're an admin."),
                HttpStatus.OK);
    }
}
