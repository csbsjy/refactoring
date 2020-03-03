package com.refactoring.fcm.board.domain.repository;

import com.refactoring.fcm.board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
