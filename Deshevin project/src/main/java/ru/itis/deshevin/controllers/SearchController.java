package ru.itis.deshevin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.deshevin.dto.DrugDto;
import ru.itis.deshevin.security.details.UserEntityDetails;
import ru.itis.deshevin.services.DrugService;
import ru.itis.deshevin.services.SearchService;
import ru.itis.deshevin.services.UserService;

import java.util.Set;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private final DrugService drugService;
    private final UserService userService;

    @GetMapping
    public String getDrugSearchPage(@AuthenticationPrincipal UserEntityDetails userEntityDetails, Model model,
                                        @RequestParam Optional<String> prefixParam) {
        String prefix = prefixParam.orElse("");
        model.addAttribute("user", userService.getUserByAuth(userEntityDetails).orElse(null));
        model.addAttribute("drugs", drugService.getAllDrugs(prefix));
        return "search-drug";
    }

    @GetMapping("/analogue/{id}")
    public String getAnalogueSearchPage(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                        @PathVariable UUID id,
                                        Model model) {
        model.addAttribute("user", userEntityDetails == null ? null : userEntityDetails.getUserEntity());
        model.addAttribute("drug", drugService.getDrugById(id));
        model.addAttribute("analogues", searchService.getDrugsWithSameAnalogueClassAs(id));
        return "analogue-drugs";
    }
}
