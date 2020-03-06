package com.fcm.refactoring.user.repository;

import com.fcm.refactoring.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
