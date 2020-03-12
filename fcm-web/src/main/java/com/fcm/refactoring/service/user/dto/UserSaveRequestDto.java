package com.fcm.refactoring.service.user.dto;

import com.fcm.refactoring.user.Gender;
import com.fcm.refactoring.user.UserType;
import com.fcm.refactoring.user.domain.User;

import javax.validation.constraints.NotBlank;

public class UserSaveRequestDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private int age;

    @NotBlank
    private Gender gender;

    @NotBlank
    private UserType userType;

    public User toEntity() {

        return User.builder()
                .userId(this.userId)
                .userName(this.userName)
                .age(this.age)
                .gender(this.gender)
                .userType(this.userType)
                .build();
    }

    public boolean isValidPassword() {
        return password.equals(confirmPassword);
    }

}

