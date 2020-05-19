package com.voting.service.controller;

import com.voting.service.VoteService;
import com.voting.service.payload.ApiResponse;
import com.voting.service.payload.VoteRequest;
import com.voting.service.security.CurrentUser;
import com.voting.service.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("votings/{votingId}/votes")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> vote(@CurrentUser UserPrincipal currentUser,
                                  @PathVariable Long votingId,
                                  @Valid @RequestBody VoteRequest voteRequest) {
        voteService.vote(votingId, voteRequest, currentUser);

        return ResponseEntity.ok().body(new ApiResponse(true, "Vote registered successfully."));
    }
}
