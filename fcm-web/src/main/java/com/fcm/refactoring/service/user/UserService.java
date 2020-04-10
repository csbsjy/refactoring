package com.fcm.refactoring.service.user;

import com.fcm.refactoring.auth.AccessUser;
import com.fcm.refactoring.auth.UserSessionManager;
import com.fcm.refactoring.dto.user.UserLoginRequestDto;
import com.fcm.refactoring.dto.user.UserResponseDto;
import com.fcm.refactoring.dto.user.UserSaveRequestDto;
import com.fcm.refactoring.user.domain.User;
import com.fcm.refactoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserSessionManager userSessionManager;

    public Long saveUser(final UserSaveRequestDto userSaveRequestDto) {
        if (!userSaveRequestDto.isValidPassword()) {
            throw new IllegalArgumentException("패스워드를 다시 입력해주십시오");
        }

        User user = userRepository.save(userSaveRequestDto.toEntity());

        return user.getId();
    }

    public UserResponseDto findByUserEmail(final String userEmail) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException(String.format("userid: %s 의 유저는 존재하지 않습니다", userEmail)));

        return new UserResponseDto(user);
    }

    public void login(UserLoginRequestDto userLoginRequestDto) {
        User findUser = userRepository.findByUserEmail(userLoginRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("%s 에 해당하는 유저정보를 찾을 수 없습니다!!")));

        if (!findUser.isValidPassword(userLoginRequestDto.getPassword())) {
            throw new IllegalArgumentException("잘못된 패스워드입니다!!");
        }

        userSessionManager.save(AccessUser.of(findUser));
    }
}
