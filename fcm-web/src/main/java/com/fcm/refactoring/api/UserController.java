package com.fcm.refactoring.api;

import com.fcm.refactoring.auth.AccessUser;
import com.fcm.refactoring.auth.CheckAuth;
import com.fcm.refactoring.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @CheckAuth
    public ResponseEntity<AccessUser> login() {
        return new ResponseEntity<>(new AccessUser(), HttpStatus.OK);
    }


}
