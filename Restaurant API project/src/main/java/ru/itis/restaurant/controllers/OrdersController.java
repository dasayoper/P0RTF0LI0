package ru.itis.restaurant.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.restaurant.OrdersApi;
import ru.itis.restaurant.dto.OrderDto;
import ru.itis.restaurant.dto.forms.OrderForm;
import ru.itis.restaurant.services.OrdersService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController implements OrdersApi {
    private final OrdersService ordersService;

    @Override
    public ResponseEntity<OrderDto> makeOrder(@RequestBody @Valid OrderForm orderForm) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ordersService.makeOrder(orderForm));
    }

    @Override
    public  ResponseEntity<OrderDto> confirmOrder(@PathVariable("order-id") Long orderId) {
        return  ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ordersService.confirmOrder(orderId));
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAllAccountOrders() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ordersService.getAllAccountOrders());
    }

}
