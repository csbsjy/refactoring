package com.refactoring.fcm.board.domain.repository;

import com.refactoring.fcm.board.domain.Article;
import com.refactoring.fcm.user.Gender;
import com.refactoring.fcm.user.domain.User;
import com.refactoring.fcm.user.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    @DisplayName("게시글 레포지토리 테스트")
    @Test
    void article() {
        User user = User.builder()
                .userName("유저A")
                .gender(Gender.WOMAN)
                .age(20)
                .build();

        userRepository.save(user);


        Article article = Article.builder()
                .subject("제목입니다.")
                .contents("내용입니다.")
                .user(user)
                .build();


        articleRepository.save(article);


        Optional<Article> findedArticle = articleRepository.findById(article.getId());


    }

}
