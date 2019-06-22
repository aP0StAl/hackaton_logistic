package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.domain.Route;
import ru.hackaton.logistic.domain.enums.OrderJoinStatus;
import ru.hackaton.logistic.repository.RouteRepository;
import ru.hackaton.logistic.repository.OrderRepository;
import ru.hackaton.logistic.request.OrderRouteJoinRequest;

@Service
@RequiredArgsConstructor
public class OrderRouteJoinService {
    private final RouteRepository routeRepository;
    private final OrderRepository orderRepository;

    public Order joinOrder(OrderRouteJoinRequest joinRequest) {
        Order ord = orderRepository.findById(joinRequest.getOrderId()).orElse(null);
        Route rt = routeRepository.findById(joinRequest.getRouteId()).orElse(null);

        if (ord.getOwnerUserId() == rt.getOwnerUserId()) {
            joinWithStatus(ord, rt, OrderJoinStatus.ACCEPTED);
        } else {
            joinWithStatus(ord, rt, OrderJoinStatus.PENDING);
        }

        return ord;
    }

    private void joinWithStatus(Order ord, Route rt, OrderJoinStatus status) {
        ord.setJoinStatus(status);
        ord.setRoute(rt);
        orderRepository.save(ord);
    }
}
