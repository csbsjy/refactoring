package com.fcm.refactoring.service.board;


import com.fcm.refactoring.board.domain.Article;
import com.fcm.refactoring.board.repository.ArticleRepository;
import com.fcm.refactoring.service.board.dto.ArticleListResponseDto;
import com.fcm.refactoring.service.board.dto.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    // TODO: Comment Count 만 받아오는 쿼리를 별도로 만들자
    @Transactional(readOnly = true)
    public List<ArticleListResponseDto> findAllList() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(ArticleListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto findById(Long id) {
        Article findedArticle = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("id가 %d인 게시글이 없습니다.", id)));
        return new ArticleResponseDto(findedArticle);
    }

}
