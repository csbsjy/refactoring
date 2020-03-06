package com.fcm.refactoring.board.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleUpdateRequestDto {
    private String userId;
    private String subject;
    private String content;

    @Builder
    public ArticleUpdateRequestDto(String userId, String subject, String content) {
        this.userId = userId;
        this.subject = subject;
        this.content = content;
    }

}
