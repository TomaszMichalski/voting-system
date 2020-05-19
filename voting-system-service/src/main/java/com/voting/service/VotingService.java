package com.voting.service;

import com.voting.db.VoterRepository;
import com.voting.db.VotingRepository;
import com.voting.model.Option;
import com.voting.model.Voter;
import com.voting.model.Voting;
import com.voting.service.payload.VotingRequest;
import com.voting.service.payload.VotingResponse;
import com.voting.service.security.UserPrincipal;
import com.voting.service.utils.VotingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VotingService {

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private VoterRepository voterRepository;

    public List<VotingResponse> getAllVotings(UserPrincipal currentUser) {
        List<Voting> votings;
        boolean isAdmin = currentUser.isAdmin();
        if (isAdmin) {
            votings = votingRepository.findAll();
        } else {
            votings = votingRepository.findByVoters_Id(currentUser.getId());
        }
        return votings.stream()
                .map(e -> VotingMapper.mapVotingToVotingResponse(e, isAdmin))
                .collect(Collectors.toList());
    }

    public Voting createVoting(VotingRequest votingRequest) {
        Voting voting = new Voting();
        voting.setName(votingRequest.getName());
        voting.setStart(votingRequest.getStart());
        voting.setEnd(votingRequest.getEnd());
        voting.setSingleChoice(votingRequest.getSingleChoice());

        new LinkedHashSet<>(votingRequest.getOptions()).forEach(e ->
                voting.addOption(new Option(e.getName())));

        Set<Voter> voters = voterRepository.findByIdIn(votingRequest.getVoterIds());
        voting.setVoters(voters);

        return votingRepository.save(voting);
    }
}
