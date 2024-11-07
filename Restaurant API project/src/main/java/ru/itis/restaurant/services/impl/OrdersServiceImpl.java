package ru.itis.restaurant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.restaurant.dto.OrderDto;
import ru.itis.restaurant.dto.forms.OrderForm;
import ru.itis.restaurant.exceptions.ItemNotFoundException;
import ru.itis.restaurant.exceptions.OrderNotFoundException;
import ru.itis.restaurant.models.Account;
import ru.itis.restaurant.models.MenuItem;
import ru.itis.restaurant.models.Order;
import ru.itis.restaurant.repositories.MenuItemsRepository;
import ru.itis.restaurant.repositories.OrdersRepository;
import ru.itis.restaurant.services.OrdersService;
import ru.itis.restaurant.services.SecurityService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final SecurityService securityService;
    private final OrdersRepository ordersRepository;
    private final MenuItemsRepository menuItemsRepository;

    @Override
    public OrderDto makeOrder(OrderForm orderForm) {
        Account account = securityService.getAuthorizedAccount();

        Set<MenuItem> items = orderForm.getItemIds().stream()
                .map(id -> menuItemsRepository.findById(id).orElseThrow((Supplier<RuntimeException>) ()
                        -> new ItemNotFoundException("Menu item with this id not found")))
                .collect(Collectors.toSet());

        Order newOrder = Order.builder()
                .destinationAddress(orderForm.getDestinationAddress())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .account(account)
                .status(Order.Status.DELIVERING)
                .items(items)
                .totalPrice(items.stream().mapToInt(MenuItem::getPrice).sum())
                .build();

        return OrderDto.from(ordersRepository.save(newOrder));
    }

    @Override
    public OrderDto confirmOrder(Long orderId) {
        Order order = ordersRepository.findById(orderId).orElseThrow((Supplier<RuntimeException>) ()
                -> new OrderNotFoundException("Order with this id not found")
        );

        order.setStatus(Order.Status.RECEIVED);

        ordersRepository.save(order);

        return OrderDto.from(order);
    }

    @Override
    public List<OrderDto> getAllAccountOrders() {
        Account account = securityService.getAuthorizedAccount();
        return OrderDto.from(account.getOrders().stream().toList());
    }


}
