package ru.hackaton.logistic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hackaton.logistic.domain.*;
import ru.hackaton.logistic.request.*;
import ru.hackaton.logistic.response.RouteWrapper;
import ru.hackaton.logistic.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
    private final OrderService orderService;
    private final RouteService routeService;
    private final CarService carService;
    private final UsrService usrService;
    private final OrderRouteJoinService orderRouteJoinService;
    private final SetJoinStatusService setJoinStatusService;
    private final GetRouteListForOrderService getRouteListForOrderService;
    private final CommisVoyageurTaskService commisVoyageurTaskService;

    @PostMapping("/order")
    public Long save_order(@RequestHeader("user_id") Long user_id, @RequestBody OrderSaveRequest order){
        return orderService.saveOrder(order, user_id).getId();
    }

    @GetMapping("/order_list")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/order_list_by_route")
    public List<Order> getAllOrders(@RequestParam Long routeId){
        return orderService.getOrdersByRoute(routeId);
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
    public List<RouteWrapper> getAllRoutes(){
        return routeService.wrapList(routeService.getAllRoutes());
    }

    @GetMapping("/my_route_list")
    public List<RouteWrapper> getMyRoutes(@RequestHeader("user_id") Long userId){
        return routeService.wrapList(routeService.getAllRoutes(userId));
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

    @PostMapping("/join_order")
    public void joinOrder(@RequestBody OrderRouteJoinRequest orderRouteJoinRequest){
        log.info(orderRouteJoinRequest.toString());
        orderRouteJoinService.joinOrder(orderRouteJoinRequest);
    }

    @PutMapping("/order_status")
    public void acceptOrder(@RequestBody SetJoinStatusRequest joinStatusRequest){
        setJoinStatusService.setStatus(joinStatusRequest);
    }

    @GetMapping("/fitting_routes")
    public List<Route> getFittingOrders(@RequestParam Long orderId){
        return getRouteListForOrderService.getFittingRoutes(orderId);
    }

    @PutMapping("/switch_route")
    public void switchRoute(@RequestParam SwitchRouteRequest switchRouteRequest){
        if(switchRouteRequest.getClose()){
            routeService.setCloseRoute(switchRouteRequest.getRouteId());
        } else {
            routeService.setOpenRoute(switchRouteRequest.getRouteId());
        }
    }

    @GetMapping("/best_routing")
    public List<GeoPoint> getBestRouting(@RequestParam Long routeId){
        Route route = routeService.getById(routeId);
        GeoPoint startPoint = route.getLoadingPoint();
        List<Order> orders = orderService.getAcceptedOrdersByRoute(routeId);
        List<GeoPoint> startPoints = orders.stream().map(Order::getLoadingPoint).collect(Collectors.toList());
        List<GeoPoint> finishPoints = orders.stream().map(Order::getDestinationPoint).collect(Collectors.toList());
        finishPoints.add(route.getDestinationPoint());
        return commisVoyageurTaskService.getPath(startPoint, startPoints, finishPoints);
    }
}
