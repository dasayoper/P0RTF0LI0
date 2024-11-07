package ru.itis.deshevin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.deshevin.dto.CategoryDto;
import ru.itis.deshevin.security.details.UserEntityDetails;
import ru.itis.deshevin.services.CategoryService;

import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String getCategoryPage(@AuthenticationPrincipal UserEntityDetails userEntityDetails, Model model) {
        model.addAttribute("user", userEntityDetails.getUserEntity());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "category";
    }

    @PostMapping
    public String saveCategory(@ModelAttribute CategoryDto categoryDto) {
        categoryService.saveCategory(categoryDto);
        return "redirect:category";
    }

    @DeleteMapping
    public String deleteCategory(@RequestParam String id) {
        categoryService.deleteCategory(id);
        return "redirect:category";
    }
}
