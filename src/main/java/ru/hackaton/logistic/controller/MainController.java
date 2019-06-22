package ru.hackaton.logistic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hackaton.logistic.domain.Car;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.domain.Route;
import ru.hackaton.logistic.request.CarSaveRequest;
import ru.hackaton.logistic.request.OrderSaveRequest;
import ru.hackaton.logistic.request.RouteSaveRequest;
import ru.hackaton.logistic.service.CarService;
import ru.hackaton.logistic.service.OrderService;
import ru.hackaton.logistic.service.RouteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final OrderService orderService;
    private final RouteService routeService;
    private final CarService carService;

    @PostMapping("/order")
    public Long save_order(@RequestBody OrderSaveRequest order){
        return orderService.saveOrder(order).getId();
    }

    @GetMapping("/order_list")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/route")
    public Long save_route(@RequestBody RouteSaveRequest route){
        return routeService.saveRoute(route).getId();
    }

    @GetMapping("/route_list")
    public List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }

    @PostMapping("/car")
    public Long save_car(@RequestBody CarSaveRequest car){
        return carService.saveCar(car).getId();
    }

    @GetMapping("/car_list")
    public List<Car> getAllCars(){
        return carService.getAll();
    }
}
