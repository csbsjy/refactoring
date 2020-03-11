package com.fcm.refactoring.board.repository;

import com.fcm.refactoring.board.repository.dao.ArticleRow;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<ArticleRow> findAllArticleAndCommentCount();
}
