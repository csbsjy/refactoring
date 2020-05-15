package com.fcm.refactoring.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@Profile({"dev", "prod"})
@RequiredArgsConstructor
public class DefaultUserSessionManager implements UserSessionManager {
    private static final String SESSION_KEY = "ACCESS_USER";
    private final HttpSession httpSession;

    @Override
    public void saveUser(AccessUser accessUser) {
        httpSession.setAttribute(SESSION_KEY, accessUser);
    }

    @Override
    public void deleteUser(String sessionId) {
        httpSession.removeAttribute(SESSION_KEY);
    }

    @Override
    public AccessUser findAccessUser() {
        return (AccessUser) httpSession.getAttribute(SESSION_KEY);
    }
}
