package com.fcm.refactoring.board.repository;

import com.fcm.refactoring.board.dto.ArticleListResponseDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.fcm.refactoring.board.domain.QArticle.article;
import static com.fcm.refactoring.board.domain.QComment.comment;
import static com.querydsl.core.types.ExpressionUtils.count;

@RequiredArgsConstructor
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ArticleListResponseDto> findAllArticleAndCommentCount() {
        return queryFactory.select(Projections.fields(ArticleListResponseDto.class,
                ExpressionUtils.as(
                        JPAExpressions.select(count(comment.id))
                                .from(comment)
                                .where(comment.article.eq(article)),
                        "commentCount"
                )))
                .from(article)
                .fetch();
    }
}
