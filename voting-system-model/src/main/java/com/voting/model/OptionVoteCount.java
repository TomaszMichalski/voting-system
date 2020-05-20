package com.voting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OptionVoteCount {

    private final Long optionId;
    private final Long voteCount;
}
