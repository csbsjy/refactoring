package com.fcm.refactoring.dto.user;

import com.fcm.refactoring.user.domain.Gender;
import com.fcm.refactoring.user.domain.User;
import com.fcm.refactoring.user.domain.UserType;

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

    public boolean isValidPassword() {
        return password.equals(confirmPassword);
    }

    public User toEntity() {
        return new User(userEmail, password, userName, age, gender, userType);
    }
}

