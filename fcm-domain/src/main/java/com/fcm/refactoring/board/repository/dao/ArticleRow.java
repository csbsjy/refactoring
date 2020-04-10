package com.fcm.refactoring.board.repository.dao;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleRow {
    private Long id;
    private String userId;
    private String subject;
    private LocalDateTime createDateTime;
    private Long commentCount;

    @Builder
    public ArticleRow(Long id, String userId, String subject, LocalDateTime createDateTime, Long commentCount) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.createDateTime = createDateTime;
        this.commentCount = commentCount;
    }
}
