package com.fcm.refactoring.auth;

import com.fcm.refactoring.user.UserType;
import com.fcm.refactoring.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessUser {
    private String userId;
    private String userName;
    private UserType userType;

    @Builder
    public AccessUser(String userId, String userName, UserType userType) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
    }

    public static AccessUser of(User user) {
        return AccessUser.builder()
                .userId(user.getUserEmail())
                .userName(user.getUserName())
                .userType(user.getUserType())
                .build();

    }
}
