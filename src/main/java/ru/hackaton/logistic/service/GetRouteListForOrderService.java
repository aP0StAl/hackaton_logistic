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

    public ArrayList<Route> getFittingRoutes(GetRouteListForOrderRequest req) {
        Order ord = orderRepository.findById(req.getOrderId()).orElse(null);

        ArrayList<Route> userRoutesList = routeRepository.findAllByOwnerUserId(ord.getOwnerUserId());
        ArrayList<Route> openRoutesList = routeRepository.findAllByIsOpen(true);

        ArrayList<Route> userFittingList = new ArrayList<Route>();

        for (Route rt : userRoutesList) {
            if (isFitting(ord, rt)) {
                userFittingList.add(rt);
            }
        }

        for (Route rt : openRoutesList) {
            if (rt.getOwnerUserId() != ord.getOwnerUserId() && isFitting(ord, rt)) {
                userFittingList.add(rt);
            }
        }

        return userFittingList;
    }

    private double distOnSphere(GeoPoint a, GeoPoint b) {
        double ac = Math.acos(Math.sin(a.getLat()) * Math.sin(b.getLat()) +
                Math.cos(a.getLat()) * Math.cos(b.getLat()) * Math.cos(a.getLon() - b.getLon()));
        return ac * 6371;
    }

    private boolean isFitting(Order ord, Route rt) {
        return distOnSphere(ord.getLoadingPoint(), rt.getLoadingPoint()) < rt.getDeliveryRadius() &&
                distOnSphere(ord.getDestinationPoint(), rt.getDestinationPoint()) < rt.getDeliveryRadius();
    }

}
