package com.voting.service.payload;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Getter
public class VotingResponse {

    private final Long id;
    private final String name;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final Boolean isExpired;
    private final Boolean singleChoice;
    private final List<OptionResponse> options;
    private final Set<VoterSummary> voters;

}
