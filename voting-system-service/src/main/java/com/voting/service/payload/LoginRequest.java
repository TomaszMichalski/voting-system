package com.voting.service.payload;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
