package com.example.animals_shop.controller;

import com.example.animals_shop.model.UserType;
import com.example.animals_shop.security.SpringUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal SpringUser springUser) {
        if (springUser.getUser().getUserType() == UserType.ADMIN) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }

}
