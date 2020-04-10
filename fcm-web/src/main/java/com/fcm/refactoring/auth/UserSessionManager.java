package com.fcm.refactoring.auth;

public interface UserSessionManager {
    void save(AccessUser accessUser);

    String remove(String sessionId);

    AccessUser findUser();
}
