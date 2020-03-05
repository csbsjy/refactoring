package com.refactoring.fcm.board.service;

import com.refactoring.fcm.board.dto.ArticleListResponseDto;
import com.refactoring.fcm.board.dto.ArticleResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.refactoring.fcm.user.UserType.TRAINER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @DisplayName("게시글 리스트를 불러오는 테스트")
    @Test
    void articleList() {
        List<ArticleListResponseDto> articleListResponseDtos = articleService.findAllList();

        assertThat(articleListResponseDtos.size()).isEqualTo(3);
    }

    @DisplayName("존재하는 id에 해당하는 게시글을 불러오는 테스트")
    @Test
    void articleById() {
        ArticleResponseDto articleResponseDto = articleService.findById(1L);

        assertAll(
                () -> assertThat(articleResponseDto.getUserId()).isEqualTo("a1010100z@naver.com"),
                () -> assertThat(articleResponseDto.getContents()).isEqualTo("글 내용입니다"),
                () -> assertThat(articleResponseDto.getSubject()).isEqualTo("글 제목입니다"),
                () -> assertThat(articleResponseDto.getUserType()).isEqualTo(TRAINER)

        );
    }

}
