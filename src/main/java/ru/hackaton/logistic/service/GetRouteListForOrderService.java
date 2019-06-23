package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.domain.Route;
import ru.hackaton.logistic.repository.OrderRepository;
import ru.hackaton.logistic.repository.RouteRepository;
import ru.hackaton.logistic.request.GetRouteListForOrderRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRouteListForOrderService {
    private final RouteRepository routeRepository;
    private final OrderRepository orderRepository;
    private final DistanceService distanceService;
    private final RouteService routeService;

    public List<Route> getFittingRoutes(Long orderId) {
        Order ord = orderRepository.findById(orderId).orElse(null);
        List<Route> userRoutesList;
        try {
            userRoutesList = routeService.getAllRoutes(ord.getUsr().getId());
        } catch (Exception e) {
            userRoutesList = new ArrayList<>();
        }
        List<Route> openRoutesList = routeRepository.findAllByIsOpen(true);

        List<Route> userFittingList = new ArrayList<>();

        for (Route rt : userRoutesList) {
            if (isFitting(ord, rt)) {
                userFittingList.add(rt);
            }
        }

        for (Route rt : openRoutesList) {
            if (((ord.getUsr() == null) || (rt.getUsr().getId() != ord.getUsr().getId())) && isFitting(ord, rt)) {
                userFittingList.add(rt);
            }
        }

        return userFittingList;
    }

    private boolean isFitting(Order ord, Route rt) {
        double dr = rt.getDeliveryRadius() == null ? 15000.0 : rt.getDeliveryRadius();
        double d1 = distanceService.getDistance(ord.getLoadingPoint(), rt.getLoadingPoint());
        double d2 = distanceService.getDistance(ord.getDestinationPoint(), rt.getDestinationPoint());
        return (d1 < dr) && (d2 < dr);
    }

}
