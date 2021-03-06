package com.fcm.refactoring.board.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String subject;

    private String contents;

    private boolean display;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

    public Long delete() {
        this.display = false;
        return this.id;
    }

    @Builder
    public Article(Long userId, String subject, String contents, boolean display, List<Comment> comments, LocalDateTime createDateTime, LocalDateTime modifiedDateTime) {
        this.userId = userId;
        this.subject = subject;
        this.contents = contents;
        this.display = display;
        this.comments = comments;
        this.createDateTime = createDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }

}
