package com.refactoring.fcm.board.domain.repository;

import com.refactoring.fcm.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
