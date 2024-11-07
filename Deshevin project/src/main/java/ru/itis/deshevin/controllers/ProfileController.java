package ru.itis.deshevin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.deshevin.security.details.UserEntityDetails;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping
    public String getProfilePage(@AuthenticationPrincipal UserEntityDetails userEntityDetails, Model model) {
        model.addAttribute("user", userEntityDetails.getUserEntity());
        return "profile";
    }
}
