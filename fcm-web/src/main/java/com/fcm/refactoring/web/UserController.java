package com.fcm.refactoring.web;

import com.fcm.refactoring.auth.AccessUser;
import com.fcm.refactoring.auth.SessionManager;
import com.fcm.refactoring.dto.user.UserLoginDto;
import com.fcm.refactoring.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SessionManager sessionManager;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto loginDto) {
        AccessUser loginUser = userService.login(loginDto);
        sessionManager.saveUser(loginUser);
        return "home";
    }
}
