package ru.hackaton.logistic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackaton.logistic.domain.Route;
import ru.hackaton.logistic.request.OrderSaveRequest;
import ru.hackaton.logistic.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final OrderService orderService;

    @PostMapping("/save_order")
    public Long save_order(@RequestBody OrderSaveRequest order){
        System.out.println("TEst");
        return orderService.saveOrder(order).getId();
    }
}
