package com.fcm.refactoring.auth;

import com.fcm.refactoring.user.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
class DefaultUserSessionManagerTest {


    MockHttpSession httpSession = new MockHttpSession();
    MockHttpServletRequest servletRequest = new MockHttpServletRequest();
    UserSessionManager userSessionManager = new DefaultUserSessionManager(httpSession);

    @DisplayName("save하면 HttpSession에 AccessUser로 저장된다")
    @Test
    void saveAccessUser() {
        //given
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest));
        servletRequest.setSession(httpSession);
        AccessUser accessUser = new AccessUser("a1010100z", "서재연", UserType.TRAINER);

        //when
        userSessionManager.saveUser(accessUser);

        //then
        AccessUser access_user = (AccessUser) servletRequest.getSession().getAttribute("ACCESS_USER");
        System.out.println(access_user);
    }
}
