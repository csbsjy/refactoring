package com.fcm.refactoring.service.user.dto;

import com.fcm.refactoring.user.Gender;
import com.fcm.refactoring.user.UserType;
import com.fcm.refactoring.user.domain.User;

import javax.validation.constraints.NotBlank;

public class UserSaveRequestDto {

    @NotBlank
    private String userEmail;

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
                .userEmail(this.userEmail)
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

