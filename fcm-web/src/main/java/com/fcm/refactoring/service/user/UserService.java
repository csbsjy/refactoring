package com.fcm.refactoring.service.user;

import com.fcm.refactoring.service.user.dto.UserResponseDto;
import com.fcm.refactoring.service.user.dto.UserSaveRequestDto;
import com.fcm.refactoring.user.domain.User;
import com.fcm.refactoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long saveUser(final UserSaveRequestDto userSaveRequestDto) {
        if (!userSaveRequestDto.isValidPassword()) {
            throw new IllegalArgumentException("패스워드를 다시 입력해주십시오");
        }

        User user = userRepository.save(userSaveRequestDto.toEntity());

        return user.getId();
    }

    public UserResponseDto findByUserId(final String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("userid: %s 의 유저는 존재하지 않습니다", userId)));

        return new UserResponseDto(user);
    }
}
