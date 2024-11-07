package ru.itis.eyejust.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.eyejust.dto.SignUpDto;
import ru.itis.eyejust.exceptions.UserAlreadyExistsException;
import ru.itis.eyejust.services.SignUpService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/sign-up")
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(Authentication authentication,
                                Model model) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        if (!model.containsAttribute("signUpDto")) {
            model.addAttribute("signUpDto", new SignUpDto());
        }
        return "sign-up";
    }

    @PostMapping
    public String signUp(@ModelAttribute(name = "signUpDto") SignUpDto signUpDto,
                         Model model) {
        try {
            signUpService.signUp(signUpDto);
            return "redirect:/profile";
        } catch (UserAlreadyExistsException ex) {
            model.addAttribute("errorMessage",
                    "Пользователь с введенным адресом электронной почты уже существует");
            model.addAttribute("signUpDto", signUpDto);
            return "sign-up";
        }
    }
}
