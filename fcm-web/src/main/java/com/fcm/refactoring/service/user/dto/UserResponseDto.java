package com.fcm.refactoring.service.user.dto;

import com.fcm.refactoring.user.Gender;
import com.fcm.refactoring.user.UserType;
import com.fcm.refactoring.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {

    private Long id;
    private String userEmail;
    private String userName;
    private UserType userType;
    private Gender gender;

    @Builder
    public UserResponseDto(String userEmail, String userName, UserType userType, Gender gender) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userType = userType;
        this.gender = gender;
    }

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
        this.gender = user.getGender();
        this.userType = user.getUserType();
    }
}
