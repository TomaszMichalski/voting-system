package com.voting.service;

import com.voting.db.VoteRepository;
import com.voting.db.VoterRepository;
import com.voting.db.VotingRepository;
import com.voting.model.Option;
import com.voting.model.OptionVoteCount;
import com.voting.model.Vote;
import com.voting.model.Voter;
import com.voting.model.Voting;
import com.voting.service.exception.BadRequestException;
import com.voting.service.exception.ResourceNotFoundException;
import com.voting.service.exception.UnauthorizedException;
import com.voting.service.payload.VotingRequest;
import com.voting.service.payload.VotingResponse;
import com.voting.service.payload.VotingResultsResponse;
import com.voting.service.security.UserPrincipal;
import com.voting.service.utils.VotingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        Map<Long, Optional<List<Long>>> votingIdToUserSelectedOptionIds = votings.stream()
                .collect(Collectors.toMap(
                        Voting::getId,
                        e -> getUserSelectedOptionIds(e.getId(), currentUser)
                ));

        return votings.stream().map(e -> {
            List<Long> userSelectedOptionIds = votingIdToUserSelectedOptionIds.get(e.getId()).orElse(null);
            return VotingMapper.mapVotingToVotingResponse(e, userSelectedOptionIds, isAdmin);
        }).collect(Collectors.toList());
    }

    public VotingResponse getVotingResponseById(Long votingId, UserPrincipal currentUser) {
        Voting voting = getVotingById(votingId, currentUser);
        List<Long> userSelectedOptionIds = getUserSelectedOptionIds(votingId, currentUser).orElse(null);

        return VotingMapper.mapVotingToVotingResponse(voting, userSelectedOptionIds, currentUser.isAdmin());
    }

    public VotingResultsResponse getVotingResultsById(Long votingId, UserPrincipal currentUser) {
        Voting voting = getVotingById(votingId, currentUser);

        if (!currentUser.isAdmin() && LocalDateTime.now().isBefore(voting.getEnd())) {
            throw new BadRequestException("Voting has not ended.");
        }

        List<OptionVoteCount> voteCounts = voteRepository.countByVotingIdGroupByOptionId(votingId);
        Map<Long, Long> optionIdsToVoteCounts = voteCounts.stream()
                .collect(Collectors.toMap(OptionVoteCount::getOptionId, OptionVoteCount::getVoteCount));

        List<Long> userSelectedOptionIds = getUserSelectedOptionIds(votingId, currentUser).orElse(null);

        return VotingMapper.mapVotingToVotingResultsResponse(voting, optionIdsToVoteCounts, userSelectedOptionIds);
    }

    private Optional<List<Long>> getUserSelectedOptionIds(Long votingId, UserPrincipal currentUser) {
        if (!currentUser.isAdmin()) {
            List<Vote> userVotes = voteRepository.findByVoter_IdAndVoter_Votings_Id(currentUser.getId(), votingId);
            return Optional.of(userVotes.stream()
                    .map(e -> e.getOption().getId())
                    .collect(Collectors.toList()));
        }
        return Optional.empty();
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
