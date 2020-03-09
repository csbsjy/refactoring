package com.fcm.refactoring.service.board;


import com.fcm.refactoring.board.domain.Article;
import com.fcm.refactoring.board.dto.ArticleListResponseDto;
import com.fcm.refactoring.board.dto.ArticleResponseDto;
import com.fcm.refactoring.board.dto.ArticleUpdateRequestDto;
import com.fcm.refactoring.board.repository.ArticleRepository;
import com.fcm.refactoring.user.domain.User;
import com.fcm.refactoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

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

    public Long write(ArticleUpdateRequestDto articleUpdateRequestDto){
        User user = userRepository.findByUserId(articleUpdateRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s 아이디의 유저는 없습니다.", articleUpdateRequestDto.getUserId())));
        Article article = Article.builder()
                .user(user)
                .subject(articleUpdateRequestDto.getSubject())
                .build();

        articleRepository.save(article);

        return article.getId();
    }

}
