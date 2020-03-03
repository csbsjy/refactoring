package com.refactoring.fcm.board.domain;

import com.refactoring.fcm.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String subject;

    private String contents;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @Builder
    public Article(User user, String subject, String contents) {
        this.user = user;
        this.subject = subject;
        this.contents = contents;
    }
}
