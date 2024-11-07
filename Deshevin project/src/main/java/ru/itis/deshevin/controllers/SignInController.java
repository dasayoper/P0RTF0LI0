package ru.itis.deshevin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/sign-in")
public class SignInController {

    @GetMapping
    public String getSignInPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "sign-in";
    }
}
