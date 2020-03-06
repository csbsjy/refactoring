package com.fcm.refactoring.board.repository;

import com.fcm.refactoring.board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
