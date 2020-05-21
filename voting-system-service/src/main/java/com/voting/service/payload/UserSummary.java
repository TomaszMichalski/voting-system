package com.voting.service.payload;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserSummary {

    private final Long id;
    private final String name;
    private final String email;

}
