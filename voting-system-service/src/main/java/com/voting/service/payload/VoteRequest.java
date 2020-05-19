package com.voting.service.payload;

import java.util.List;

public class VoteRequest {

    private List<Long> optionIds;

    public List<Long> getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(List<Long> optionIds) {
        this.optionIds = optionIds;
    }
}
