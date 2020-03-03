package com.refactoring.fcm.board.dto;

import com.refactoring.fcm.user.UserType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleResponseDto {

    private String userId;
    private UserType userType;

    private String subject;
    private String contents;

    @Builder
    public ArticleResponseDto(String userId, UserType userType, String subject, String contents) {
        this.userId = userId;
        this.userType = userType;
        this.subject = subject;
        this.contents = contents;
    }
}
