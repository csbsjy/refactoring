package com.fcm.refactoring.dto.board;

import com.fcm.refactoring.board.domain.Article;
import com.fcm.refactoring.utils.LocalDateTimeConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleResponseDto {

    private Long id;
    private String userId;
    private String userType;

    private String subject;
    private String contents;

    private String createDateTime;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.userId = article.getUser().getUserId();
        this.userType = article.getUser().getUserTypeName();
        this.subject = article.getSubject();
        this.contents = article.getContents();
        this.createDateTime = LocalDateTimeConverter.convert(article.getCreateDateTime());
    }
}
