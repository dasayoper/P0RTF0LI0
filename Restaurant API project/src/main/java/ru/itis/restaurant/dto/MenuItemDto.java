package ru.itis.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.restaurant.models.MenuItem;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDto {
    private Long id;

    private String title;

    private String description;

    private Integer weight;

    private Integer price;

    private String category;

    public static MenuItemDto from(MenuItem menuItem) {
        return MenuItemDto.builder()
                .id(menuItem.getId())
                .title(menuItem.getTitle())
                .description(menuItem.getDescription())
                .weight(menuItem.getWeight())
                .price(menuItem.getPrice())
                .category(menuItem.getCategory().toString())
                .build();
    }

    public static List<MenuItemDto> from(List<MenuItem> menuItems) {
        return menuItems.stream().map(MenuItemDto::from).collect(Collectors.toList());
    }
}
