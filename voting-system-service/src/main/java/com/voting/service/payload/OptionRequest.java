package com.voting.service.payload;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@EqualsAndHashCode
public class OptionRequest {

    @NotBlank
    private String name;

}
