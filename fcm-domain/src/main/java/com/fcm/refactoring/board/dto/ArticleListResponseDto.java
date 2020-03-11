package com.fcm.refactoring.board.dto;

import com.fcm.refactoring.board.repository.dao.ArticleRow;
import com.fcm.refactoring.utils.LocalDateTimeConverter;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public ArticleListResponseDto(ArticleRow articleRow) {
        this.id = articleRow.getId();
        this.userId = articleRow.getUserId();
        this.subject = articleRow.getSubject();
        this.createDateTime = LocalDateTimeConverter.convert(articleRow.getCreateDateTime());
        this.commentCount = articleRow.getCommentCount();
    }
}
