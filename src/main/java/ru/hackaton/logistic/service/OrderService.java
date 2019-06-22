package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.GeoPoint;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.repository.OrderRepository;
import ru.hackaton.logistic.request.OrderSaveRequest;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order saveOrder(OrderSaveRequest order) {
        GeoPoint loadingPoint = GeoPoint.builder()
                .name(order.getFromTitle())
                .lat(order.getFromPoint().get(0))
                .lon(order.getFromPoint().get(1))
                .build();

        GeoPoint destinationPoint = GeoPoint.builder()
                .name(order.getToTitle())
                .lat(order.getToPoint().get(0))
                .lon(order.getToPoint().get(1))
                .build();

        Order o = Order.builder()
                .loadingPoint(loadingPoint)
                .destinationPoint(destinationPoint)
                .volume(order.getVolume())
                .weight(order.getWeight())
                .expirationDate(order.getExpirationDate())
                .build();

        return orderRepository.save(o);
    }
}
