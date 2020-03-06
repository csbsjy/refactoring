package com.fcm.refactoring.service.board;


import com.fcm.refactoring.board.domain.Article;
import com.fcm.refactoring.board.dto.ArticleListResponseDto;
import com.fcm.refactoring.board.dto.ArticleResponseDto;
import com.fcm.refactoring.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleListResponseDto> findAllList() {
        return articleRepository.findAllArticleAndCommentCount();
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto findById(Long id) {
        Article findedArticle = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("id가 %d인 게시글이 없습니다.", id)));
        return new ArticleResponseDto(findedArticle);
    }

}
