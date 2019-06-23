package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.*;
import ru.hackaton.logistic.domain.enums.OrderJoinStatus;
import ru.hackaton.logistic.repository.CarRepository;
import ru.hackaton.logistic.repository.OrderRepository;
import ru.hackaton.logistic.repository.RouteRepository;
import ru.hackaton.logistic.repository.UsrRepository;
import ru.hackaton.logistic.request.RouteSaveRequest;
import ru.hackaton.logistic.response.RouteWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final CarRepository carRepository;
    private final UsrRepository usrRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public Route saveRoute(RouteSaveRequest route, Long userId) {

        GeoPoint loadingPoint = GeoPoint.builder()
                .name(route.getFromTitle())
                .lat(route.getFromPoint().get(0))
                .lon(route.getFromPoint().get(1))
                .build();

        GeoPoint destinationPoint = GeoPoint.builder()
                .name(route.getToTitle())
                .lat(route.getToPoint().get(0))
                .lon(route.getToPoint().get(1))
                .build();

        Car car = null;
        if(route.getCarId() != null) {
            car = carRepository.findById(route.getCarId()).orElse(null);
        }

        Usr usr = null;
        try {
            usr = usrRepository.findById(userId).orElse(null);
        } catch (Exception e) {
        }

        Route r = Route.builder()
                .loadingPoint(loadingPoint)
                .destinationPoint(destinationPoint)
                .car(car)
                .isOpen(route.getIsOpen() == null ? true : route.getIsOpen())
                .isStraight(route.getIsStraight())
                .deliveryRadius(route.getDeliveryRadius())
                .costKg(route.getCostKg())
                .costLt(route.getCostLt())
                .usr(usr)
                .build();

        return routeRepository.save(r);
    }

    public void setOpenRoute(Long routeId) {
        Route rt = routeRepository.findById(routeId).orElse(null);
        rt.setIsOpen(true);
        routeRepository.save(rt);
    }

    public void setCloseRoute(Long routeId) {
        Route rt = routeRepository.findById(routeId).orElse(null);
        rt.setIsOpen(false);
        routeRepository.save(rt);

        List<Order> orders = orderRepository.findAll();

        for (Order ord : orders) {
            if (rt.equals(ord.getRoute()) && ord.getJoinStatus() == OrderJoinStatus.PENDING) {
                ord.setJoinStatus(OrderJoinStatus.DECLINED);
                orderRepository.save(ord);
            }
        }
    }

    public ArrayList<Order> getJointOrdersForRoute(Long routeId) { //Возвращает список принятых и ожидающих принятия заявок
        Route rt = routeRepository.findById(routeId).orElse(null);

        List<Order> orders = orderRepository.findAll();
        ArrayList<Order> acceptedOrders = new ArrayList<Order>();
        ArrayList<Order> pendingOrders = new ArrayList<Order>();
        ArrayList<Order> jointOrders = new ArrayList<Order>();

        for (Order ord : orders) {
            if (rt.equals(ord.getRoute())) {
                if (ord.getJoinStatus() == OrderJoinStatus.ACCEPTED) {
                    acceptedOrders.add(ord);
                } else if (ord.getJoinStatus() == OrderJoinStatus.PENDING) {
                    pendingOrders.add(ord);
                }
            }
        }

        jointOrders.addAll(acceptedOrders);
        jointOrders.addAll(pendingOrders);

        return jointOrders;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public List<Route> getAllRoutes(Long userId) {
        List<Route> routes = getAllRoutes();
        return routes.stream().filter(o -> o.getUsr() != null && userId.equals(o.getUsr().getId())).collect(Collectors.toList());
    }

    public RouteWrapper wrapRoute(Route route){
        List<Order> orders = orderService.getAcceptedOrdersByRoute(route.getId());
        RouteWrapper routeWrapper = new RouteWrapper(route);
        routeWrapper.setAcceptedCount((long)orders.size());
        routeWrapper.setAcceptedVolume(orders.stream().mapToDouble(Order::getVolume).sum());
        routeWrapper.setAcceptedWeight(orders.stream().mapToDouble(Order::getWeight).sum());
        return routeWrapper;
    }

    public List<RouteWrapper> wrapList(List<Route> routes){
        return routes.stream().map(this::wrapRoute).collect(Collectors.toList());
    }

}
