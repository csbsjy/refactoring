package com.fcm.refactoring.auth;

import com.fcm.refactoring.user.UserType;
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
}
