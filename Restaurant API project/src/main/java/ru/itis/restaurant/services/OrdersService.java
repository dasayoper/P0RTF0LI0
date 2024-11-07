package ru.itis.restaurant.services;

import ru.itis.restaurant.dto.OrderDto;
import ru.itis.restaurant.dto.forms.OrderForm;

import java.util.List;

public interface OrdersService {
    OrderDto makeOrder(OrderForm orderForm);

    OrderDto confirmOrder(Long orderId);

    List<OrderDto> getAllAccountOrders();
}
