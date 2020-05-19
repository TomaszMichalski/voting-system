package com.voting.service;

import com.voting.db.VoterRepository;
import com.voting.db.VotingRepository;
import com.voting.model.Option;
import com.voting.model.Voter;
import com.voting.model.Voting;
import com.voting.service.payload.VotingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class VotingService {

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private VoterRepository voterRepository;

    public Voting createVoting(VotingRequest votingRequest) {
        Voting voting = new Voting();
        voting.setName(votingRequest.getName());
        voting.setStart(votingRequest.getStart());
        voting.setEnd(votingRequest.getEnd());
        voting.setSingleChoice(votingRequest.isSingleChoice());

        new LinkedHashSet<>(votingRequest.getOptions()).forEach(e ->
                voting.addOption(new Option(e.getName())));

        Set<Voter> voters = voterRepository.findByIdIn(votingRequest.getVoterIds());
        voting.setVoters(voters);

        return votingRepository.save(voting);
    }
}
