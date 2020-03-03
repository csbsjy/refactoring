package com.refactoring.fcm.board.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArticleUpdateRequestDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @DisplayName("@Size @NotBlank")
    @Test
    void test() {
        ArticleUpdateRequestDto articleUpdateRequestDto = ArticleUpdateRequestDto.builder()
                .subject("")
                .content("")
                .build();

        Set<ConstraintViolation<ArticleUpdateRequestDto>> violations = validator.validate(articleUpdateRequestDto);

        assertTrue(!violations.isEmpty());

    }

    @DisplayName("@Size로 Null 값 검증 되나?")
    @Test
    void testnull() {
        ArticleUpdateRequestDto articleUpdateRequestDto = ArticleUpdateRequestDto.builder()
                .subject(null)
                .content(null)
                .build();

        Set<ConstraintViolation<ArticleUpdateRequestDto>> violations = validator.validate(articleUpdateRequestDto);

        assertTrue(!violations.isEmpty());

    }

    @DisplayName("@Size @NotNull 테스트")
    @Test
    void testSizeNotNull() {
        ArticleUpdateRequestDto articleUpdateRequestDto = ArticleUpdateRequestDto.builder()
                .subject(null)
                .content("10자이하")
                .build();

        Set<ConstraintViolation<ArticleUpdateRequestDto>> violations = validator.validate(articleUpdateRequestDto);

        List<ConstraintViolation> constraintViolations = violations.stream()
                .collect(Collectors.toList());
        assertThat(violations.size()).isEqualTo(2); // subject의 NotNull, content 의 Size
        assertAll(
                () -> assertThat(constraintViolations.get(0).getMessage()).isEqualTo("subject에는 null이 들어올 수 없습니다"),
                () -> assertThat(constraintViolations.get(1).getMessage()).isEqualTo("content는 10자 이상이어야 합니다")
        );

    }

}
