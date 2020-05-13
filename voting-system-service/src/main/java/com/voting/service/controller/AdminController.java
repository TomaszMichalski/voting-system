package com.voting.service.controller;

import com.voting.model.Voting;
import com.voting.service.vote.VoterService;
import com.voting.service.vote.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VotingService votingService;

    @Autowired
    private VoterService voterService;

    @RequestMapping("/voting")
    public String main(Model model) {
        model.addAttribute("votings", votingService.listAll());
        return "admin/main";
    }

    @GetMapping("/voting/add")
    public String showVotingForm(Model model) {
        model.addAttribute("voters", voterService.listAll());
        model.addAttribute("voting", new Voting());
        return "admin/voting-form";
    }

    @PostMapping("/voting/add")
    public String addVoting(@ModelAttribute("voting") Voting voting) {
        votingService.save(voting);
        return "redirect:/admin/voting";
    }
}
