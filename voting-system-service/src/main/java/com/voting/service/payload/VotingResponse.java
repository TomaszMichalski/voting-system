package com.voting.service.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private final Boolean isActive;
    private final Boolean isExpired;
    private final Boolean singleChoice;
    private final List<OptionResponse> options;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<Long> selectedOptionIds;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Set<UserSummary> voters;

}
