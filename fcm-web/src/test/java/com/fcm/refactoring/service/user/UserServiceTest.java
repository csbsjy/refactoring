package com.fcm.refactoring.service.user;

import com.fcm.refactoring.auth.AccessUser;
import com.fcm.refactoring.auth.CustomUserSessionManager;
import com.fcm.refactoring.dto.user.UserLoginRequestDto;
import com.fcm.refactoring.dto.user.UserResponseDto;
import com.fcm.refactoring.dto.user.UserSaveRequestDto;
import com.fcm.refactoring.user.Gender;
import com.fcm.refactoring.user.UserType;
import com.fcm.refactoring.user.domain.User;
import com.fcm.refactoring.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Mock 테스트")
class UserServiceTest {

    @Autowired
    MockHttpSession httpSession;

    @Autowired
    MockHttpServletRequest mockHttpServletRequest;

    @Mock
    UserRepository userRepository;

    @Mock
    CustomUserSessionManager customUserSessionManager;

    @InjectMocks
    UserService userService;


    @DisplayName("유저아이디로 유저를 조회한다")
    @Test
    void findByUserId() {

        User user = createUser();
        when(userRepository.findByUserId("a1010100z@naver.com")).thenReturn(Optional.of(user));

        UserResponseDto userResponseDto = userService.findByUserId("a1010100z@naver.com");
        assertAll(
                () -> assertThat(userResponseDto.getUserName()).isEqualTo("서재연"),
                () -> assertThat(userResponseDto.getGender()).isEqualTo(Gender.WOMAN),
                () -> assertThat(userResponseDto.getUserType()).isEqualTo(UserType.TRAINER)
        );

    }

    @DisplayName("없는 아이디로 유저조회시 익셉션던진다")
    @Test
    void findByWrongUserId() {
        assertThatThrownBy(() -> userService.findByUserId("a1010100z@naver.com"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("userid: a1010100z@naver.com 의 유저는 존재하지 않습니다");

    }

    private UserSaveRequestDto createUserSaveReqDto() {
        return UserSaveRequestDto.builder()
                .userId("a1010100z@naver.com")
                .password("1234")
                .confirmPassword("1234")
                .gender(Gender.WOMAN)
                .userType(UserType.TRAINER)
                .age(26)
                .userName("서재연")
                .build();
    }

    private User createUser() {
        return User.builder()
                .userId("a1010100z@naver.com")
                .userName("서재연")
                .password("1234")
                .age(26)
                .userType(UserType.TRAINER)
                .gender(Gender.WOMAN)
                .build();
    }

    @DisplayName("로그인에 성공하면 Session에 유저정보가 저장된다")
    @Test
    void login() {
        //given
        UserLoginRequestDto userLoginRequestDto = createUserLoginDto();
        when(userRepository.findByUserId(userLoginRequestDto.getUserId())).thenReturn(Optional.of(createUser()));
        AccessUser accessUser = AccessUser.of(createUser());

        //when
        userService.login(userLoginRequestDto);

        //then
        assertThat(customUserSessionManager.findUser()).isEqualTo(accessUser);
    }

    private UserLoginRequestDto createUserLoginDto() {
        return UserLoginRequestDto.builder()
                .userId("a1010100z@naver.com")
                .password("1234")
                .build();
    }
}
