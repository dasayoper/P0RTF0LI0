package ru.itis.restaurant.services;

import ru.itis.restaurant.dto.MenuItemDto;
import ru.itis.restaurant.dto.MenuItemsPage;
import ru.itis.restaurant.dto.forms.MenuItemForm;

public interface MenuItemsService {
    MenuItemDto addMenuItem(MenuItemForm menuItemForm);
    MenuItemDto updateMenuItem(Long itemId, MenuItemForm newData);
    MenuItemsPage getMenuItems(int page);
}
