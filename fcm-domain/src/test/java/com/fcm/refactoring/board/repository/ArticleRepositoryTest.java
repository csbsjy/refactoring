package com.fcm.refactoring.board.repository;

import com.fcm.refactoring.board.repository.dao.ArticleRow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @DisplayName("display 이 true 인 게시글 리스트를 출력한다")
    @Test
    void displayTest() {
        List<ArticleRow> findArticleRowResponse = articleRepository.findAllArticleAndCommentCount();
        assertThat(findArticleRowResponse.size()).isEqualTo(3);
    }
}
