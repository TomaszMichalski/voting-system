package com.voting.service.payload;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VoterSummary {

    private final long id;
    private final String name;
    private final String email;

}
