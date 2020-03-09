package com.fcm.refactoring.board.dto;

import com.fcm.refactoring.utils.LocalDateTimeConverter;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ArticleListResponseDto {
    private Long id;
    private String userId;
    private String subject;
    private String createDateTime;
    private Long commentCount;

    @Builder
    public ArticleListResponseDto(Long id, String userId, String subject, LocalDateTime createDateTime, Long commentCount) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.createDateTime = LocalDateTimeConverter.convert(createDateTime);
        this.commentCount = commentCount;
    }

    public ArticleListResponseDto(Long id, String userId, String subject, String createDateTime, Long commentCount) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.createDateTime = createDateTime;
        this.commentCount = commentCount;
    }
}
