package com.refactoring.fcm.board.dto;

import com.refactoring.fcm.board.domain.Article;
import com.refactoring.fcm.utils.LocalDateTimeConverter;
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
