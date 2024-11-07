package ru.itis.eyejust.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.eyejust.dto.UserInfoFormDto;
import ru.itis.eyejust.security.details.UserEntityDetails;
import ru.itis.eyejust.services.UserEntityService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final UserEntityService userEntityService;

    @GetMapping
    public String getProfilePage(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                 Model model) {
        model.addAttribute("user", userEntityDetails.getUserEntity());
        return "profile";
    }

    @PostMapping("/update")
    public String updateUserInfo(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                 @ModelAttribute UserInfoFormDto userInfoFormDto,
                                 Model model) {;
        userEntityDetails.setUserEntity(userEntityService.updateUserInfo(userInfoFormDto, userEntityDetails.getUserEntity().getId()));
        model.addAttribute("user", userEntityDetails.getUserEntity());
        return "redirect:/profile";
    }

    @GetMapping("/update")
    public String getUserInfoForm(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                  Model model) {
        model.addAttribute("user", userEntityDetails.getUserEntity());
        return "user-info-form";
    }
}
