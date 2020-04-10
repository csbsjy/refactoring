package com.fcm.refactoring.dto.board;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {
    private Long id;
    private String userEmail;
    private String content;
    private LocalDateTime createDateTime;

    @Builder
    public CommentResponseDto(Long id, String userEmail, String content, LocalDateTime createDateTime) {
        this.id = id;
        this.userEmail = userEmail;
        this.content = content;
        this.createDateTime = createDateTime;
    }
}
