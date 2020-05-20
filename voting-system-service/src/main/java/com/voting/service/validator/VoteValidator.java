package com.voting.service.validator;

import com.voting.db.VoteRepository;
import com.voting.model.Voter;
import com.voting.model.Voting;
import com.voting.service.exception.BadRequestException;
import com.voting.service.payload.VoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.StreamSupport;

@Component
public class VoteValidator {

    @Autowired
    private VoteRepository voteRepository;

    public void validate(VoteRequest voteRequest, Voting voting, Voter voter) {
        if (voting.getStart().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Voting has not started.");
        }

        if (voting.getEnd().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Voting has ended.");
        }

        if (!voterRegisteredForVoting(voter, voting)) {
            throw new BadRequestException("Voter not registered for voting.");
        }

        if (voterAlreadyVoted(voter, voting)) {
            throw new BadRequestException("Already voted in this voting.");
        }

        if (voting.getSingleChoice() && voteRequest.getOptionIds().size() > 1) {
            throw new BadRequestException("Voting allows only single choice.");
        }
    }

    private boolean voterAlreadyVoted(Voter voter, Voting voting) {
        return StreamSupport.stream(voteRepository.findAll().spliterator(), false)
                .filter(vote -> vote.getId().getVotingId().equals(voting.getId()))
                .anyMatch(vote -> vote.getId().getVoterId().equals(voter.getId()));
    }

    private boolean voterRegisteredForVoting(Voter voter, Voting voting) {
        return voting.getVoters().contains(voter);
    }
}
