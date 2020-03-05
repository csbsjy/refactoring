package com.refactoring.fcm.board.service;

import com.refactoring.fcm.board.domain.Article;
import com.refactoring.fcm.board.domain.repository.ArticleRepository;
import com.refactoring.fcm.board.dto.ArticleListResponseDto;
import com.refactoring.fcm.board.dto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    // TODO: Comment Count 만 받아오는 쿼리를 별도로 만들자
    public List<ArticleListResponseDto> findAllList() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(ArticleListResponseDto::new)
                .collect(Collectors.toList());
    }

    public ArticleResponseDto findById(Long id) {
        Article findedArticle = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("id가 %d인 게시글이 없습니다.", id)));
        return new ArticleResponseDto(findedArticle);
    }

}
