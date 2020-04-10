package com.fcm.refactoring.dto.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginRequestDto {

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String password;

    @Builder
    public UserLoginRequestDto(@NotNull @NotEmpty String userId, @NotNull @NotEmpty String password) {
        this.userId = userId;
        this.password = password;
    }
}
