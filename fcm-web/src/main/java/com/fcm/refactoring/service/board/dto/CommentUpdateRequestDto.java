package com.fcm.refactoring.service.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateRequestDto {
    private String userEmail;
    private String content;

    @Builder
    public CommentUpdateRequestDto(String userEmail, String content) {
        this.userEmail = userEmail;
        this.content = content;
    }
}
