package ru.hackaton.logistic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.domain.Route;
import ru.hackaton.logistic.domain.RouteMap;
import ru.hackaton.logistic.request.OrderSaveRequest;
import ru.hackaton.logistic.service.GoogleMapService;
import ru.hackaton.logistic.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final GoogleMapService googleMapService;
    private final OrderService orderService;

    @GetMapping("/hi")
    public String hello(){
        return "hello, world!";
    }

    @GetMapping("/google_route/{p1}/{p2}/")
    public RouteMap getGoogleRoute(@PathVariable String p1, @PathVariable String p2){
        return googleMapService.getRoute(p1, p2);
    }

    @GetMapping("/random_route")
    public List<Route> getRandomRoute(){
        List<Route> result = new ArrayList<>();
        for(int i=0;i<10;i++){
            result.add(Route.getRandom());
        }
        return result;
    }

    @PostMapping("/save_order")
    public Long save_order(@RequestBody OrderSaveRequest order){
        System.out.println("TEst");
        return orderService.saveOrder(order).getId();
    }
}
