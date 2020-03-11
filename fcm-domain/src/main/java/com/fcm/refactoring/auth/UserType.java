package com.fcm.refactoring.auth;

import lombok.Getter;

@Getter
public enum UserType {
    MEMBER("일반회원"), TRAINER("트레이너"), MANAGER("관리자");

    private String name;

    UserType(String name) {
        this.name = name;
    }
}
