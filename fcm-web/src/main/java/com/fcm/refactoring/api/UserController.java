package com.fcm.refactoring.api;

import com.fcm.refactoring.dto.user.UserLoginRequestDto;
import com.fcm.refactoring.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        userService.login(userLoginRequestDto);
        return ResponseEntity.ok(null);
    }

}
