package com.refactoring.fcm.board.dto;

import com.refactoring.fcm.board.domain.Article;
import com.refactoring.fcm.user.UserType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleResponseDto {

    private String userId;
    private UserType userType;

    private String subject;
    private String contents;

    private List<CommentResponseDto> commentResponseDtos;

    public ArticleResponseDto(Article article) {
        this.userId = article.getUser().getUserId();
        this.userType = article.getUser().getUserType();
        this.subject = article.getSubject();
        this.contents = article.getContents();
        this.commentResponseDtos = article.getComments().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Builder
    public ArticleResponseDto(String userId, UserType userType, String subject, String contents, List<CommentResponseDto> commentResponseDtos) {
        this.userId = userId;
        this.userType = userType;
        this.subject = subject;
        this.contents = contents;
        this.commentResponseDtos = commentResponseDtos;
    }
}
