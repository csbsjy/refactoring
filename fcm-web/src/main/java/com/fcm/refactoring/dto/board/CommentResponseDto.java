package com.fcm.refactoring.dto.board;

import com.fcm.refactoring.board.domain.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {
    private Long id;
    private String userId;
    private String content;
    private LocalDateTime createDateTime;

    @Builder
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUser().getUserId();
        this.content = comment.getContent();
        this.createDateTime = comment.getCreateDateTime();
    }
}
