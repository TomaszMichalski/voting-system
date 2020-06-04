package com.voting.model;

import java.util.List;
import java.util.stream.Collectors;

public class VoteResult {

    private Long votingId;

    private List<Long> optionIds;

    public VoteResult(Long votingId, List<Long> optionIds) {
        this.votingId = votingId;
        this.optionIds = optionIds;
    }

    @Override
    public String toString() {
        return "Vote in voting " + votingId + ", options " + toOptionLog(optionIds);
    }

    private String toOptionLog(List<Long> optionIds) {
        return optionIds.stream()
                .map(optionId -> String.valueOf(optionId.longValue()))
                .collect(Collectors.joining(","));
    }
}
