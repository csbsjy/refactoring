package com.fcm.refactoring.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userEmail;
    private String password;
    private String userName;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @CreatedDate
    private LocalDateTime createDateTime;

    private boolean enable;

    public User(String userEmail, String password, String userName, int age, Gender gender, UserType userType) {
        this.userEmail = userEmail;
        this.password = password;
        this.userName = userName;
        this.age = age;
        this.gender = gender;
        this.userType = userType;
        this.enable = true;
    }

    public boolean isValidPassword(final String password) {
        return this.password.equals(password);
    }

    public String getUserTypeName() {
        return userType.getName();
    }


}
