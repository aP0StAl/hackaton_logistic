package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.GeoPoint;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.domain.Route;
import ru.hackaton.logistic.repository.OrderRepository;
import ru.hackaton.logistic.repository.RouteRepository;
import ru.hackaton.logistic.request.GetRouteListForOrderRequest;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GetRouteListForOrderService {
    private final RouteRepository routeRepository;
    private final OrderRepository orderRepository;
    private final DistanceService distanceService;

    public ArrayList<Route> getFittingRoutes(GetRouteListForOrderRequest req) {
        Order ord = orderRepository.findById(req.getOrderId()).orElse(null);

        ArrayList<Route> userRoutesList = routeRepository.findAllByOwnerUserId(ord.getUsr().getId());
        ArrayList<Route> openRoutesList = routeRepository.findAllByIsOpen(true);

        ArrayList<Route> userFittingList = new ArrayList<Route>();

        for (Route rt : userRoutesList) {
            if (isFitting(ord, rt)) {
                userFittingList.add(rt);
            }
        }

        for (Route rt : openRoutesList) {
            if (rt.getOwnerUserId() != ord.getUsr().getId() && isFitting(ord, rt)) {
                userFittingList.add(rt);
            }
        }

        return userFittingList;
    }

    private boolean isFitting(Order ord, Route rt) {
        return (distanceService.getDistance(ord.getLoadingPoint(), rt.getLoadingPoint()) < rt.getDeliveryRadius()) &&
                (distanceService.getDistance(ord.getDestinationPoint(), rt.getDestinationPoint()) < rt.getDeliveryRadius());
    }

}
