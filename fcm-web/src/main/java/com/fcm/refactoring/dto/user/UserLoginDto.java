package com.fcm.refactoring.dto.user;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class UserLoginDto {
    @Email
    private final String email;
    @NotNull
    @NotEmpty
    private final String password;

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
