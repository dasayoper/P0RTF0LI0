package ru.itis.deshevin.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.deshevin.security.details.UserEntityDetails;
import ru.itis.deshevin.services.FilesService;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FilesService filesService;

    @GetMapping("/homepage")
    public String getHomePage(@AuthenticationPrincipal UserEntityDetails userEntityDetails, Model model) {
        model.addAttribute("user", userEntityDetails == null ? null : userEntityDetails.getUserEntity());
        return "home";
    }

    @GetMapping("/")
    public String redirectHomePage() {
        return "redirect:/homepage";
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        filesService.addFileToResponse(fileName, response);
    }

}