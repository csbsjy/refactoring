package com.fcm.refactoring.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
@Profile({"dev", "prod"})
public class UserSessionManager implements SessionManager {

    private static final String SESSION_KEY = "ACCESS_USER";

    private final HttpSession httpSession;

    @Override
    public void saveUser(AccessUser accessUser) {
        httpSession.setAttribute(SESSION_KEY, accessUser);
    }

    @Override
    public AccessUser extractUser() {
        return (AccessUser) httpSession.getAttribute(SESSION_KEY);
    }
}
