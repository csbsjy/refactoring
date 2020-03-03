package com.refactoring.fcm.user.domain;

import com.refactoring.fcm.user.Gender;
import com.refactoring.fcm.user.UserType;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @CreatedDate
    private LocalDateTime createDateTime;

    private boolean isEnable;

    @Builder
    public User(String userName, int age, Gender gender) {
        this.userName = userName;
        this.age = age;
        this.gender = gender;
    }


}
