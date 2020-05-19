package com.voting.service.controller;

import com.voting.model.Voting;
import com.voting.service.VotingService;
import com.voting.service.payload.ApiResponse;
import com.voting.service.payload.VotingRequest;
import com.voting.service.security.CurrentUser;
import com.voting.service.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/votings")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> getVotings(@CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(votingService.getAllVotings(currentUser));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createVoting(@Valid @RequestBody VotingRequest votingRequest) {
        Voting voting = votingService.createVoting(votingRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{votingId}")
                .buildAndExpand(voting.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Voting created successfully."));
    }

}
