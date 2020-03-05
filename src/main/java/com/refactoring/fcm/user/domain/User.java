package com.refactoring.fcm.user.domain;

import com.refactoring.fcm.user.Gender;
import com.refactoring.fcm.user.UserType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;
    private String userName;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @CreatedDate
    private LocalDateTime createDateTime;

    private boolean enable;

    @Builder
    public User(String userId, String userName, int age, Gender gender) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.gender = gender;
    }

    public String getUserName() {
        return this.userName;
    }

}
