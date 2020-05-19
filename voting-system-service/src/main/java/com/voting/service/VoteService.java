package com.voting.service;

import com.voting.db.VoteRepository;
import com.voting.db.VoterRepository;
import com.voting.db.VotingRepository;
import com.voting.model.Option;
import com.voting.model.Vote;
import com.voting.model.Voter;
import com.voting.model.Voting;
import com.voting.service.exception.BadRequestException;
import com.voting.service.exception.ResourceNotFoundException;
import com.voting.service.payload.VoteRequest;
import com.voting.service.security.UserPrincipal;
import com.voting.service.validator.VoteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private VoteValidator voteValidator;

    public void vote(Long votingId, VoteRequest voteRequest, UserPrincipal currentUser) {
        Voting voting = votingRepository.findById(votingId)
                .orElseThrow(() -> new ResourceNotFoundException("Voting", "id", votingId));

        Voter voter = voterRepository.findById(currentUser.getId())
                .orElseThrow(() -> new BadRequestException("User is invalid."));

        voteValidator.validate(voteRequest, voting, voter);

        LocalDateTime voteTime = LocalDateTime.now();

        voteRequest.getOptionIds()
                .stream()
                .map(optionId -> toVotingOption(voting, optionId))
                .map(option -> toVote(voter, option, voting, voteTime))
                .forEach(voteRepository::save);
    }

    private Option toVotingOption(Voting voting, Long optionId) {
        return voting.getOptions()
                .stream()
                .filter(votingOption -> votingOption.getId() == optionId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Option", "id", optionId));
    }

    private Vote toVote(Voter voter, Option selectedOption, Voting voting, LocalDateTime voteTime) {
        return new Vote(voter, selectedOption, voting, voteTime);
    }
}
