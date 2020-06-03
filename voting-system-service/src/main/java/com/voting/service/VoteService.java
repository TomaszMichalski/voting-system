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
import com.voting.service.publish.ResultPublisher;
import com.voting.service.security.UserPrincipal;
import com.voting.service.validator.VoteValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteService {
    private static final Logger logger = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private VoteValidator voteValidator;

    @Autowired
    private ResultPublisher resultPublisher;

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

        logger.info("Vote in voting " + voting.getId() + ", options " + toOptionLog(voteRequest.getOptionIds()));

        resultPublisher.publishResults(voting);
    }

    private Option toVotingOption(Voting voting, Long optionId) {
        return voting.getOptions()
                .stream()
                .filter(votingOption -> votingOption.getId().equals(optionId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Option", "id", optionId));
    }

    private Vote toVote(Voter voter, Option selectedOption, Voting voting, LocalDateTime voteTime) {
        return new Vote(voter, selectedOption, voting, voteTime);
    }

    private String toOptionLog(List<Long> optionIds) {
        return optionIds.stream()
                .map(optionId -> String.valueOf(optionId.longValue()))
                .collect(Collectors.joining(","));
    }
}
