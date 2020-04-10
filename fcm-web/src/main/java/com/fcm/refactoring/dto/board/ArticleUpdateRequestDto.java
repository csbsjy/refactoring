package com.fcm.refactoring.dto.board;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleUpdateRequestDto {
    private String userEmail;
    private String subject;
    private String content;

    @Builder
    public ArticleUpdateRequestDto(String userEmail, String subject, String content) {
        this.userEmail = userEmail;
        this.subject = subject;
        this.content = content;
    }
}
