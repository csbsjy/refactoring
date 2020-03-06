package com.fcm.refactoring.board.repository;

import com.fcm.refactoring.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
