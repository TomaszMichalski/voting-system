package com.voting.service.payload;

import javax.validation.constraints.NotBlank;

public class OptionRequest {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
