package ru.itis.eyejust.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/sign-in")
public class SignInController {

    @GetMapping
    public String getSignInPage(Authentication authentication,
                                @RequestParam(name = "error", required = false) String error,
                                Model model) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        if (error != null) {
            model.addAttribute("errorMessage", "Неверный адрес электронной почты или пароль");
        }
        return "sign-in";
    }
}