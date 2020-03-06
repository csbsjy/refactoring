package com.fcm.refactoring.board.domain;

import com.fcm.refactoring.user.domain.User;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String subject;

    private String contents;

    private boolean display;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @CreatedDate
    private LocalDateTime localDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

    @Builder
    public Article(User user, String subject, String contents) {
        this.user = user;
        this.subject = subject;
        this.contents = contents;
    }

    public Long delete() {
        this.display = false;
        return this.id;
    }
}
