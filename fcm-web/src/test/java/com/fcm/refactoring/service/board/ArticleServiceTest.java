package com.fcm.refactoring.service.board;

import com.fcm.refactoring.board.domain.Article;
import com.fcm.refactoring.board.dto.ArticleListResponseDto;
import com.fcm.refactoring.board.dto.ArticleResponseDto;
import com.fcm.refactoring.board.dto.ArticleUpdateRequestDto;
import com.fcm.refactoring.board.repository.ArticleRepository;
import com.fcm.refactoring.user.Gender;
import com.fcm.refactoring.user.UserType;
import com.fcm.refactoring.user.domain.User;
import com.fcm.refactoring.utils.LocalDateTimeConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ArticleService Mock UnitTest")
class ArticleServiceTest {

    @Mock
    ArticleRepository articleRepository;

    @InjectMocks
    ArticleService articleService;

    @DisplayName("게시글 리스트를 불러오는 테스트")
    @Test
    void articleList() {
        //given
        List<ArticleListResponseDto> articleListResponseDtos = makeArticleListResponseDto();

        when(articleRepository.findAllArticleAndCommentCount())
                .thenReturn(articleListResponseDtos);

        //when
        List<ArticleListResponseDto> findArticleListResponseDtos = articleService.findAllList();

        //then
        assertAll(
                () -> assertThat(findArticleListResponseDtos.size()).isEqualTo(2),
                () -> assertThat(findArticleListResponseDtos.get(0).getCommentCount()).isEqualTo(3),
                () -> assertThat(findArticleListResponseDtos.get(1).getSubject()).isEqualTo("제목 2")
        );
    }


    @DisplayName("존재하는 id에 해당하는 게시글을 불러오는 테스트")
    @Test
    void articleById() {
        //given
        Article article = makeArticle();
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        //when
        ArticleResponseDto articleResponseDto = articleService.findById(1L);
        assertAll(
                () -> assertThat(articleResponseDto.getUserId()).isEqualTo("a1010100z"),
                () -> assertThat(articleResponseDto.getContents()).isEqualTo("내용 1"),
                () -> assertThat(articleResponseDto.getSubject()).isEqualTo("제목 1"),
                () -> assertThat(articleResponseDto.getUserType()).isEqualTo("트레이너")

        );
    }



    private Article makeArticle() {
        User user = new User("a1010100z", "재연", 26, Gender.WOMAN,
                UserType.TRAINER, LocalDateTime.now(), true);
        Article article = new Article(user, "제목 1", "내용 1", true,
                Collections.EMPTY_LIST, LocalDateTime.now(), LocalDateTime.now());
        return article;
    }


    // QueryDsl
    private List<ArticleListResponseDto> makeArticleListResponseDto() {
        ArticleListResponseDto articleListResponseDto1 = new ArticleListResponseDto(
                1L, "a1010100z", "제목 1", "2020-03-10", 3L);

        ArticleListResponseDto articleListResponseDto2 = new ArticleListResponseDto(
                2L, "a1010100z", "제목 2", "2020-03-10", 1L);

        return Arrays.asList(articleListResponseDto1, articleListResponseDto2);
    }
}
