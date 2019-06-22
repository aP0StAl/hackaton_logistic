package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.GeoPoint;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.repository.GeoPointRepository;
import ru.hackaton.logistic.repository.OrderRepository;
import ru.hackaton.logistic.request.OrderSaveRequest;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final GeoPointRepository geoPointRepository;

    public Order saveOrder(OrderSaveRequest order) {
        GeoPoint loadingPoint = GeoPoint.builder().name(order.getLoadingPointName()).build();
        GeoPoint destinationPoint = GeoPoint.builder().name(order.getDestinationPointName()).build();

        Order o = Order.builder()
                .loadingPoint(loadingPoint)
                .destinationPoint(destinationPoint)
                .volume(order.getVolume())
                .weight(order.getWeight())
                .build();
       return orderRepository.save(o);
    }
}
