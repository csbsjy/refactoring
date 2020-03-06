package com.fcm.refactoring.service.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateRequestDto {
    private String userId;
    private String content;

    @Builder
    public CommentUpdateRequestDto(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}
