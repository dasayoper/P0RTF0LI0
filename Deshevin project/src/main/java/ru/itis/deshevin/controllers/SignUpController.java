package ru.itis.deshevin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.deshevin.dto.SignUpDto;
import ru.itis.deshevin.services.SignUpService;

@Controller
@RequestMapping("/sign-up")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(@ModelAttribute(name = "signUpDto") SignUpDto signUpDto, Authentication authentication) {
        if (authentication != null){
            return "redirect:/profile";
        }
        return "sign-up";
    }

    @PostMapping
    public String signUp(@ModelAttribute(name = "signUpDto") SignUpDto signUpDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "sign-up";
        }
        signUpService.signUp(signUpDto);
        return "redirect:/profile";
    }
}
