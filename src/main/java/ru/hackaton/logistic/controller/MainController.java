package ru.hackaton.logistic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.request.OrderSaveRequest;
import ru.hackaton.logistic.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final OrderService orderService;
    @PostMapping("/order")
    public Long save_order(@RequestBody OrderSaveRequest order){
        return orderService.saveOrder(order).getId();
    }

    @GetMapping("/order_list")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
}
