package com.voting.service.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class VotingResultsResponse {

    private final Long id;
    private final String name;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final Boolean isExpired;
    private final Boolean singleChoice;
    private final List<OptionResultsResponse> options;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<Long> selectedOptionIds;

}
