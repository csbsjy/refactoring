package com.fcm.refactoring.auth;

import com.fcm.refactoring.user.domain.UserType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessUser {
    private String email;
    private String userName;
    private UserType userType;

    @Builder
    public AccessUser(String email, String userName, UserType userType) {
        this.email = email;
        this.userName = userName;
        this.userType = userType;
    }

    public static AccessUser of(String email, String userName, UserType userType) {
        return new AccessUser(email, userName, userType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessUser that = (AccessUser) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(userName, that.userName) &&
                userType == that.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, userName, userType);
    }
}
