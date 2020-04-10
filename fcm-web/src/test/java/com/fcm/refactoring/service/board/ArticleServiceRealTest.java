package com.fcm.refactoring.service.board;

import com.fcm.refactoring.board.domain.Article;
import com.fcm.refactoring.board.repository.ArticleRepository;
import com.fcm.refactoring.dto.board.ArticleUpdateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@DisplayName("ArticleService Using h2 DB Test")
public class ArticleServiceRealTest {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleRepository articleRepository;

    @DisplayName("유저, 제목, 내용으로 게시글 정상저장 테스트")
    @Test
    void saveArticle() {

        ArticleUpdateRequestDto articleUpdateRequestDto = new ArticleUpdateRequestDto("a1010100z@naver.com", "제목입니다", "내용입니다");

        Long id = articleService.write(articleUpdateRequestDto);

        Article article = articleRepository.findById(id).get();

        assertAll(
                () -> assertThat(article.getUserId()).isEqualTo(1L),
                () -> assertThat(article.getSubject()).isEqualTo("제목입니다")
        );
    }
}
