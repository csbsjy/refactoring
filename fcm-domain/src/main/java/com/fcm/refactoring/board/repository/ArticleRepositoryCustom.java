package com.fcm.refactoring.board.repository;

import com.fcm.refactoring.board.dto.ArticleListResponseDto;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<ArticleListResponseDto> findAllArticleAndCommentCount();
}
