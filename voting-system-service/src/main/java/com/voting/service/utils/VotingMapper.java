package com.voting.service.utils;

import com.voting.model.Voting;
import com.voting.service.payload.OptionResponse;
import com.voting.service.payload.VoterSummary;
import com.voting.service.payload.VotingResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VotingMapper {

    public static VotingResponse mapVotingToVotingResponse(Voting voting, boolean isAdmin) {
        LocalDateTime now = LocalDateTime.now();
        List<OptionResponse> optionResponses = voting.getOptions().stream()
                .map(option -> OptionResponse.builder()
                        .id(option.getId())
                        .name(option.getName())
                        .build())
                .collect(Collectors.toList());

        VotingResponse.VotingResponseBuilder votingResponse = VotingResponse.builder()
                .id(voting.getId())
                .name(voting.getName())
                .start(voting.getStart())
                .end(voting.getEnd())
                .singleChoice(voting.getSingleChoice())
                .isExpired(voting.getEnd().isBefore(now))
                .options(optionResponses);

        if (isAdmin) {
            Set<VoterSummary> voterSummaries = voting.getVoters().stream()
                    .map(voter -> VoterSummary.builder()
                            .id(voter.getId())
                            .name(voter.getName())
                            .email(voter.getEmail())
                            .build())
                    .collect(Collectors.toSet());
            votingResponse.voters(voterSummaries);
        }

        return votingResponse.build();
    }
}
