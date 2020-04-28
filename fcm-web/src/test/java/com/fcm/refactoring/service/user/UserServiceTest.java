package com.fcm.refactoring.service.user;

import com.fcm.refactoring.auth.AccessUser;
import com.fcm.refactoring.dto.user.UserLoginDto;
import com.fcm.refactoring.user.domain.Gender;
import com.fcm.refactoring.user.domain.User;
import com.fcm.refactoring.user.domain.UserType;
import com.fcm.refactoring.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final String USER_EMAIL = "a1010100z@naver.com";

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @DisplayName("유저가 로그인을 성공하면 AccessUser를 반환한다")
    @Test
    void loginSuccess() {
        //given
        when(userRepository.findByUserEmail(USER_EMAIL)).thenReturn(createUser());
        UserLoginDto loginUser = new UserLoginDto(USER_EMAIL, "1234");
        AccessUser expectAccessUser = new AccessUser(USER_EMAIL, "SEO", UserType.TRAINER);

        //when
        AccessUser accessUser = userService.login(loginUser);

        //then
        assertThat(accessUser).isNotNull();
        assertThat(accessUser).isEqualTo(expectAccessUser);
    }

    private Optional<User> createUser() {
        return Optional.of(new User(1L, USER_EMAIL, "1234", "SEO", 20, Gender.WOMAN, UserType.TRAINER, LocalDateTime.now(), true));
    }

    @DisplayName("없는 아이디로 로그인하면 Exception을 던진다")
    @Test
    void loginFailure() {
        //given
        String wrongEmail = "email";
        when(userRepository.findByUserEmail(wrongEmail)).thenReturn(Optional.empty());
        UserLoginDto loginUser = new UserLoginDto(wrongEmail, "1234");

        //when //then
        assertThatThrownBy(() -> userService.login(loginUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("email 에 해당하는 유저는 없습니다");

    }

    @DisplayName("유저가 패스워드를 잘못 입력하면 Exception을 던진다")
    @Test
    void loginFailureByWrongPassword() {
        //given
        when(userRepository.findByUserEmail(USER_EMAIL)).thenReturn(createUser());
        UserLoginDto loginUser = new UserLoginDto(USER_EMAIL, "wrongPassword");

        //when //then
        assertThatThrownBy(() -> userService.login(loginUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("패스워드가 틀렸습니다!");

    }
}
