package com.fcm.refactoring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @GetMapping("/login")
    public String login(HttpServletRequest servletRequest) {
        return "home";
    }
}
