package com.abakli.controller;

import com.abakli.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    private final UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public String wel(Model model) {

        model.addAttribute("isAdmin", userService.isAdmin());

        return "welcome";
    }
}
