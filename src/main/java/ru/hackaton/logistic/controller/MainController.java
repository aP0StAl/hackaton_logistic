package ru.hackaton.logistic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackaton.logistic.domain.Car;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.domain.Route;
import ru.hackaton.logistic.domain.Usr;
import ru.hackaton.logistic.request.CarSaveRequest;
import ru.hackaton.logistic.request.LoginRequest;
import ru.hackaton.logistic.request.OrderSaveRequest;
import ru.hackaton.logistic.request.RouteSaveRequest;
import ru.hackaton.logistic.service.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final OrderService orderService;
    private final RouteService routeService;
    private final CarService carService;
    private final UsrService usrService;

    @PostMapping("/order")
    public Long save_order(@RequestHeader("user_id") Long user_id, @RequestBody OrderSaveRequest order){
        return orderService.saveOrder(order, user_id).getId();
    }

    @GetMapping("/order_list")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/my_order_list")
    public List<Order> getMyOrders(@RequestHeader("user_id") Long userId){
        return orderService.getAllOrders(userId);
    }

    @PostMapping("/route")
    public Long save_route(@RequestHeader("user_id") Long userId, @RequestBody RouteSaveRequest route){
        return routeService.saveRoute(route, userId).getId();
    }

    @GetMapping("/route_list")
    public List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }

    @GetMapping("/my_route_list")
    public List<Route> getMyRoutes(@RequestHeader("user_id") Long userId){
        return routeService.getAllRoutes(userId);
    }

    @PostMapping("/car")
    public Long save_car(@RequestBody CarSaveRequest car){
        return carService.saveCar(car).getId();
    }

    @GetMapping("/car_list")
    public List<Car> getAllCars(){
        return carService.getAll();
    }

    @PostMapping("/login")
    public Usr login(@RequestBody LoginRequest loginRequest){
        return usrService.login(loginRequest);
    }
}
