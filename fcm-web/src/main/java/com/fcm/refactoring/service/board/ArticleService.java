package com.fcm.refactoring.service.board;


import com.fcm.refactoring.board.domain.Article;
import com.fcm.refactoring.board.repository.ArticleRepository;
import com.fcm.refactoring.service.board.dto.ArticleListResponseDto;
import com.fcm.refactoring.service.board.dto.ArticleResponseDto;
import com.fcm.refactoring.service.board.dto.ArticleUpdateRequestDto;
import com.fcm.refactoring.user.domain.User;
import com.fcm.refactoring.user.repository.UserRepository;
import com.fcm.refactoring.utils.LocalDateTimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ArticleListResponseDto> findAllList() {
        return articleRepository.findAllArticleAndCommentCount().stream()
                .map(ArticleListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto findById(Long id) {
        Article finderArticle = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("id가 %d인 게시글이 없습니다.", id)));

        User user = userRepository.findById(finderArticle.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("id가 %s인 유저는 없습니다", finderArticle.getUserId())));

        return ArticleResponseDto.builder()
                .userEmail(user.getUserEmail())
                .userType(user.getUserType().getName())
                .contents(finderArticle.getContents())
                .createDateTime(LocalDateTimeConverter.convert(finderArticle.getCreateDateTime()))
                .subject(finderArticle.getSubject())
                .build();

    }

    @Transactional
    public Long write(ArticleUpdateRequestDto articleUpdateRequestDto) {

        User user = userRepository.findByUserEmail(articleUpdateRequestDto.getUserEmail())
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s 아이디의 유저는 없습니다", articleUpdateRequestDto.getUserEmail())));
        Article article = Article.builder()
                .userId(user.getId())
                .subject(articleUpdateRequestDto.getSubject())
                .contents(articleUpdateRequestDto.getContent())
                .build();

        articleRepository.save(article);

        return article.getId();
    }

    @Transactional
    public Long delete(Long id) {
        // TODO: 인증과정 필요
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s 번 게시글은 존재하지 않습니다", id)));
        return article.delete();
    }

}

