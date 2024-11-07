package ru.itis.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.restaurant.models.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;

    private String destinationAddress;

    private Integer totalPrice;

    private Long accountId;

    private List<MenuItemDto> itemsList;

    private String status;

    public static OrderDto from(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .destinationAddress(order.getDestinationAddress())
                .totalPrice(order.getTotalPrice())
                .accountId(order.getAccount().getId())
                .itemsList(order.getItems().stream().map(MenuItemDto::from).toList())
                .status(order.getStatus().toString())
                .build();
    }

    public static List<OrderDto> from(List<Order> orders) {
        return orders.stream().map(OrderDto::from).collect(Collectors.toList());
    }
}
