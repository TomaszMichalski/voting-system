package com.voting.service.payload;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class VoteRequest {

    @NotEmpty
    private List<Long> optionIds;

}
