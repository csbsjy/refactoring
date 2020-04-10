package com.fcm.refactoring.dto.board;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleResponseDto {

    private String userEmail;
    private String userType;

    private String subject;
    private String contents;

    private String createDateTime;

    @Builder
    public ArticleResponseDto(String userEmail, String userType, String subject, String contents, String createDateTime) {
        this.userEmail = userEmail;
        this.userType = userType;
        this.subject = subject;
        this.contents = contents;
        this.createDateTime = createDateTime;
    }
}
