package com.fcm.refactoring.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class CustomUserSessionManager implements UserSessionManager {

    private static final String USER_SESSION_KEY = "USER_SESSION";
    private final HttpSession httpSession;

    @Override
    public void save(AccessUser accessUser) {
        httpSession.setAttribute(USER_SESSION_KEY, accessUser);
    }

    @Override
    public String remove(String userId) {
        if (httpSession.getAttribute(USER_SESSION_KEY) == null) {
            throw new IllegalArgumentException(String.format("id: %s 유저가 세션에 존재하지 않습니다", userId));
        }
        return userId;
    }

    @Override
    public AccessUser findUser() {
        return (AccessUser) httpSession.getAttribute(USER_SESSION_KEY);
    }


}
