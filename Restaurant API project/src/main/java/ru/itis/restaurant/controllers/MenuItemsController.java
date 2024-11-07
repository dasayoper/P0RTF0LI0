package ru.itis.restaurant.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.restaurant.MenuItemsApi;
import ru.itis.restaurant.dto.MenuItemDto;
import ru.itis.restaurant.dto.MenuItemsPage;
import ru.itis.restaurant.dto.forms.MenuItemForm;
import ru.itis.restaurant.services.MenuItemsService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MenuItemsController implements MenuItemsApi{
    private final MenuItemsService menuItemsService;

    @Override
    public ResponseEntity<MenuItemsPage> getMenuItems(@RequestParam("page") Integer page) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuItemsService.getMenuItems(page));
    }

    @Override
    public ResponseEntity<MenuItemDto> addMenuItem(@RequestBody @Valid MenuItemForm menuItemForm) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(menuItemsService.addMenuItem(menuItemForm));
    }

    @Override
    public ResponseEntity<MenuItemDto> updateMenuItem(@PathVariable("item-id") Long itemId,
                                                      @RequestBody @Valid MenuItemForm newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(menuItemsService.updateMenuItem(itemId, newData));
    }

}
