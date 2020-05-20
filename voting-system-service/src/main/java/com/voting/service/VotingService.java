package com.voting.service;

import com.voting.db.VoteRepository;
import com.voting.db.VoterRepository;
import com.voting.db.VotingRepository;
import com.voting.model.Option;
import com.voting.model.OptionVoteCount;
import com.voting.model.Vote;
import com.voting.model.Voter;
import com.voting.model.Voting;
import com.voting.service.exception.UnauthorizedException;
import com.voting.service.exception.ResourceNotFoundException;
import com.voting.service.payload.VotingRequest;
import com.voting.service.payload.VotingResponse;
import com.voting.service.payload.VotingResultsResponse;
import com.voting.service.security.UserPrincipal;
import com.voting.service.utils.VotingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VotingService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private VotingRepository votingRepository;

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

    public VotingResponse getVotingResponseById(Long votingId, UserPrincipal currentUser) {
        Voting voting = getVotingById(votingId, currentUser);
        return VotingMapper.mapVotingToVotingResponse(voting, currentUser.isAdmin());
    }

    public VotingResultsResponse getVotingResultsById(Long votingId, UserPrincipal currentUser) {
        Voting voting = getVotingById(votingId, currentUser);

        List<OptionVoteCount> voteCounts = voteRepository.countByVotingIdGroupByOptionId(votingId);
        Map<Long, Long> optionIdsToVoteCounts = voteCounts.stream()
                .collect(Collectors.toMap(OptionVoteCount::getOptionId, OptionVoteCount::getVoteCount));

        List<Long> userSelectedOptionIds = null;
        if (!currentUser.isAdmin()) {
            List<Vote> userVotes = voteRepository.findByVoter_IdAndVoter_Votings_Id(currentUser.getId(), votingId);
            userSelectedOptionIds = userVotes.stream()
                    .map(e -> e.getOption().getId())
                    .collect(Collectors.toList());
        }

        return VotingMapper.mapVotingToVotingResultsResponse(voting, optionIdsToVoteCounts, userSelectedOptionIds);
    }

    private Voting getVotingById(Long votingId, UserPrincipal currentUser) {
        Voting voting = votingRepository.findById(votingId)
                .orElseThrow(() -> new ResourceNotFoundException("Voting", "id", votingId));

        boolean isVoterNotRegisteredForVoting = voting.getVoters().stream()
                .noneMatch(e -> e.getId().equals(currentUser.getId()));

        if (!currentUser.isAdmin() && isVoterNotRegisteredForVoting) {
            throw new UnauthorizedException();
        }

        return voting;
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
