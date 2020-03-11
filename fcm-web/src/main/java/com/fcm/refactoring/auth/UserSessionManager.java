package com.fcm.refactoring.auth;

public interface UserSessionManager<AccessUser> {
    AccessUser saveUser(AccessUser accessUser);

    AccessUser deleteUser(String sessionId);
}
