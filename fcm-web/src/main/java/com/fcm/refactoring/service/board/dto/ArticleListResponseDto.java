package com.fcm.refactoring.service.board.dto;

import com.fcm.refactoring.board.domain.Article;
import com.fcm.refactoring.utils.LocalDateTimeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleListResponseDto {
    private Long id;
    private String userId;
    private String subject;
    private String createDateTime;
    private int commentCount;

    public ArticleListResponseDto(final Article article) {
        this.id = article.getId();
        this.userId = article.getUser().getUserId();
        this.subject = article.getSubject();
        this.createDateTime = LocalDateTimeConverter.convert(article.getLocalDateTime());
        this.commentCount = article.getComments().size();
    }
}
