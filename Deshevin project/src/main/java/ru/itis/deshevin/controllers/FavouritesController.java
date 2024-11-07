package ru.itis.deshevin.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.deshevin.security.details.UserEntityDetails;
import ru.itis.deshevin.services.FavouritesService;;import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/favourites")
public class FavouritesController {
    private final FavouritesService favouritesService;

    @GetMapping
    public String getFavouritesPage(@AuthenticationPrincipal UserEntityDetails userEntityDetails, Model model) {
        model.addAttribute("user", userEntityDetails.getUserEntity());
        model.addAttribute("favourites", favouritesService.getFavouriteDrugs(
                userEntityDetails.getUserEntity().getId()));
        return "favourites";
    }

    @PostMapping("/add-to-favourites/{drug-id}")
    public void addItemToFavourites(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                      @PathVariable("drug-id") UUID drugId) {
        favouritesService.addDrugToFavourites(userEntityDetails.getUserEntity().getId(), drugId);
    }

    @DeleteMapping("/delete-from-favourites/{drug-id}")
    public void deleteItemFromFavourites(@AuthenticationPrincipal UserEntityDetails userEntityDetails,
                                           @PathVariable("drug-id") UUID drugId) {
        favouritesService.deleteDrugFromFavourites(userEntityDetails.getUserEntity().getId(), drugId);
    }
}
