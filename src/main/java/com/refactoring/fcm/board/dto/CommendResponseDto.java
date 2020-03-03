package com.refactoring.fcm.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommendResponseDto {
    private Long id;
    private String userId;
    private String content;
    private LocalDateTime createDateTime;

    @Builder
    public CommendResponseDto(Long id, String userId, String content, LocalDateTime createDateTime) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createDateTime = createDateTime;
    }
}
