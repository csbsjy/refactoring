package com.fcm.refactoring.service.board;

import com.fcm.refactoring.auth.Gender;
import com.fcm.refactoring.auth.UserType;
import com.fcm.refactoring.auth.domain.User;
import com.fcm.refactoring.board.domain.Article;
import com.fcm.refactoring.board.dto.ArticleListResponseDto;
import com.fcm.refactoring.board.dto.ArticleResponseDto;
import com.fcm.refactoring.board.repository.ArticleRepository;
import com.fcm.refactoring.board.repository.dao.ArticleRow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        List<ArticleRow> articleRows = makeArticleListResponseDto();

        when(articleRepository.findAllArticleAndCommentCount())
                .thenReturn(articleRows);

        //when
        List<ArticleListResponseDto> findArticleLists = articleService.findAllList();

        //then
        assertAll(
                () -> assertThat(findArticleLists.size()).isEqualTo(2),
                () -> assertThat(findArticleLists.get(0).getCommentCount()).isEqualTo(3),
                () -> assertThat(findArticleLists.get(1).getSubject()).isEqualTo("제목 2")
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

    @DisplayName("게시글 삭제 성공")
    @Test
    void delete() {
        Article article = makeArticle();
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        Long id = articleService.delete(1L);

        assertThat(articleRepository.findById(id).get().isDisplay()).isFalse();
    }

    @DisplayName("존재하지 않는 게시글 삭제시도 시 익셉션 던진다")
    @Test
    void deleteFail() {
        assertThatThrownBy(() -> articleService.delete(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1 번 게시글은 존재하지 않습니다");
    }


    private Article makeArticle() {
        User user = new User("a1010100z", "재연", 26, Gender.WOMAN,
                UserType.TRAINER, LocalDateTime.now(), true);
        Article article = new Article(1L, user, "제목 1", "내용 1", true,
                Collections.EMPTY_LIST, LocalDateTime.now(), LocalDateTime.now());
        return article;
    }


    // QueryDsl
    private List<ArticleRow> makeArticleListResponseDto() {
        ArticleRow articleRow1 = new ArticleRow(
                1L, "a1010100z", "제목 1", LocalDateTime.now(), 3L);

        ArticleRow articleRow2 = new ArticleRow(
                2L, "a1010100z", "제목 2", LocalDateTime.now(), 1L);

        return Arrays.asList(articleRow1, articleRow2);
    }
}
