package com.fcm.refactoring.auth;

public interface SessionManager {
    void saveUser(AccessUser accessUser);

    AccessUser extractUser();
}
