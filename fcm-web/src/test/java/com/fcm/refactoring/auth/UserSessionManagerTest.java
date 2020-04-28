package com.fcm.refactoring.auth;

import com.fcm.refactoring.user.domain.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserSessionManagerTest {

    @Mock
    MockHttpServletRequest servletRequest;

    @InjectMocks
    UserSessionManager userSessionManager;


    @BeforeEach
    void setUp() {
        MockHttpSession httpSession = new MockHttpSession();
        servletRequest = new MockHttpServletRequest();
        servletRequest.setSession(httpSession);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
        userSessionManager = new UserSessionManager(httpSession);
    }

    @DisplayName("AccessUser를 저장하면 HttpSession에 저장된다")
    @Test
    void save() {
        AccessUser accessUser = new AccessUser("email", "seo", UserType.TRAINER);
        userSessionManager.saveUser(accessUser);

        assertThat(servletRequest.getSession().getAttribute("ACCESS_USER")).isEqualTo(accessUser);
    }


}
