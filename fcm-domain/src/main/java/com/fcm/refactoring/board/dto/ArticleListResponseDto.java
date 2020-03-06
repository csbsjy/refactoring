package com.fcm.refactoring.board.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ArticleListResponseDto {
    private Long id;
    private String userId;
    private String subject;
    private String createDateTime;
    private Long commentCount;

    public ArticleListResponseDto(Long id, String userId, String subject, String createDateTime, Long commentCount) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.createDateTime = createDateTime;
        this.commentCount = commentCount;
    }
}
