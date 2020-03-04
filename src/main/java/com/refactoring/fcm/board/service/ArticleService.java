package com.refactoring.fcm.board.service;

import com.refactoring.fcm.board.domain.Article;
import com.refactoring.fcm.board.domain.repository.ArticleRepository;
import com.refactoring.fcm.board.dto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleResponseDto> findAll() {
        List<Article> articles = articleRepository.findAll();


    }


}

/**
 * public List<ArticleDTO> showAllArticles();
 * public Integer write(ArticleDTO article) throws Exception;
 * public ArticleDTO showArticleByIdx(int idx);
 * public void reviseArticle(ArticleDTO article);
 * public void deleteArticle(int idx);
 * <p>
 * public void addComment(CommentDTO comment);
 * public List<CommentDTO> showAllComments(int idx);
 **/
