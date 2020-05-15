package com.fcm.refactoring.auth;

public interface UserSessionManager {
    void saveUser(AccessUser accessUser);

    void deleteUser(String sessionId);

    AccessUser findAccessUser();
}
