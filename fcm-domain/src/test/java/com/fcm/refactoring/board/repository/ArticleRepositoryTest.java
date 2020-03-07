package com.fcm.refactoring.board.repository;

import com.fcm.refactoring.board.dto.ArticleListResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @DisplayName("display 이 true 인 게시글 리스트를 출력한다")
    @Test
    void displayTest(){
        List<ArticleListResponseDto> findArticleListResponse = articleRepository.findAllArticleAndCommentCount();
        assertThat(findArticleListResponse.size()).isEqualTo(3);
    }
}
