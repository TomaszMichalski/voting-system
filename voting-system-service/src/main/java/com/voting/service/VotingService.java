package com.voting.service;

import com.voting.db.VotingRepository;
import com.voting.model.Option;
import com.voting.model.Voting;
import com.voting.service.payload.VotingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VotingService {

    @Autowired
    private VotingRepository votingRepository;

    public Voting createVoting(VotingRequest votingRequest) {
        Voting voting = new Voting();
        voting.setName(votingRequest.getName());
        voting.setStart(votingRequest.getStart());
        voting.setEnd(votingRequest.getEnd());
        voting.setSingleChoice(votingRequest.isSingleChoice());

        List<Option> options = votingRequest.getOptions().stream()
                .map(e -> new Option(e.getName()))
                .collect(Collectors.toList());
        voting.setOptions(options);

        return votingRepository.save(voting);
    }
}
