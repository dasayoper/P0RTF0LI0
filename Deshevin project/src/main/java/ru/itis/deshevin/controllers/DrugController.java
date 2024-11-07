package ru.itis.deshevin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itis.deshevin.dto.AddDrugDto;
import ru.itis.deshevin.dto.CategoryDto;
import ru.itis.deshevin.security.details.UserEntityDetails;
import ru.itis.deshevin.services.AnalogueClassService;
import ru.itis.deshevin.services.CategoryService;
import ru.itis.deshevin.services.DrugService;
import ru.itis.deshevin.services.UserService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/drug")
@RequiredArgsConstructor
public class DrugController {

    private final AnalogueClassService analogueClassService;
    private final CategoryService categoryService;
    private final DrugService drugService;
    private final UserService userService;


    @GetMapping
    public String getAddDrugPage(@AuthenticationPrincipal UserEntityDetails userEntityDetails, Model model) {
        model.addAttribute("user", userEntityDetails.getUserEntity());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("analogues", analogueClassService.getAllAnalogueClass());
        return "add-drug";
    }

    @PostMapping
    public String saveDrug(@ModelAttribute AddDrugDto addDrugDto, RedirectAttributes redirectAttrs) {
        drugService.saveDrug(addDrugDto);
        redirectAttrs.addFlashAttribute("success", true);
        return "redirect:drug";
    }

    @GetMapping("/all")
    public String getAllDrugsPage(@AuthenticationPrincipal UserEntityDetails userEntityDetails, Model model) {
        model.addAttribute("user", userEntityDetails.getUserEntity());
        model.addAttribute("drugs", drugService.getAllDrugs(""));
        return "drug-table";
    }

    @GetMapping("/{drug-id}")
    public String getDrugInfoPage(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                  @PathVariable("drug-id") UUID id, Model model) {
        model.addAttribute("user", userService.getUserByAuth(userEntityDetails).orElse(null));
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("analogues", analogueClassService.getAllAnalogueClass());
        model.addAttribute("drug", drugService.getDrugById(id));
        return "drug-info";
    }

    @PostMapping("/{drug-id}")
    public String updateDrug(@PathVariable("drug-id") UUID id, @ModelAttribute AddDrugDto addDrugDto) {
        drugService.updateDrug(addDrugDto, id);
        return "redirect:"+id;
    }

    @DeleteMapping
    public String deleteDrug(@RequestParam UUID id) {
        drugService.deleteDrugBuId(id);
        return "redirect:drug/all";
    }

}
