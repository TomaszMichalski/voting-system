package com.voting.service.payload;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OptionResponse {

    private final long id;
    private final String name;

}
