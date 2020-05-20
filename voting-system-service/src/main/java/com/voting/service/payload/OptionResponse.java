package com.voting.service.payload;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OptionResponse {

    private final Long id;
    private final String name;

}
