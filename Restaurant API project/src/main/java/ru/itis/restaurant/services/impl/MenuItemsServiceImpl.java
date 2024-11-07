package ru.itis.restaurant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.restaurant.dto.MenuItemDto;
import ru.itis.restaurant.dto.MenuItemsPage;
import ru.itis.restaurant.dto.forms.MenuItemForm;
import ru.itis.restaurant.exceptions.ItemNotFoundException;
import ru.itis.restaurant.models.MenuItem;
import ru.itis.restaurant.repositories.MenuItemsRepository;
import ru.itis.restaurant.services.MenuItemsService;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class MenuItemsServiceImpl implements MenuItemsService {
    private final MenuItemsRepository menuItemsRepository;

    @Value("${restaurant.default-page-size}")
    private int defaultPageSize;

    @Override
    public MenuItemDto addMenuItem(MenuItemForm menuItemForm) {
        MenuItem newMenuItem = MenuItem.builder()
                .title(menuItemForm.getTitle())
                .description(menuItemForm.getDescription())
                .weight(menuItemForm.getWeight())
                .price(menuItemForm.getPrice())
                .category(MenuItem.Category.valueOf(menuItemForm.getCategory()))
                .build();

        return MenuItemDto.from(menuItemsRepository.save(newMenuItem));
    }

    @Override
    public MenuItemDto updateMenuItem(Long itemId, MenuItemForm newData) {
        MenuItem menuItem = menuItemsRepository.findById(itemId).orElseThrow((Supplier<RuntimeException>) ()
                -> new ItemNotFoundException("Menu item with this id not found")
        );
        menuItem.setTitle(newData.getTitle());
        menuItem.setDescription(newData.getDescription());
        menuItem.setWeight(newData.getWeight());
        menuItem.setPrice(newData.getPrice());
        menuItem.setCategory(MenuItem.Category.valueOf(newData.getCategory()));

        return MenuItemDto.from(menuItemsRepository.save(menuItem));
    }

    @Override
    public MenuItemsPage getMenuItems(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize, Sort.Direction.ASC, "title");
        Page<MenuItem> menuItemPage = menuItemsRepository.findAll(pageRequest);
        return MenuItemsPage.builder()
                .menuItems(MenuItemDto.from(menuItemPage.getContent()))
                .totalPages(menuItemPage.getTotalPages())
                .build();
    }
}
