package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.GeoPoint;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.domain.Usr;
import ru.hackaton.logistic.repository.OrderRepository;
import ru.hackaton.logistic.repository.UsrRepository;
import ru.hackaton.logistic.request.OrderSaveRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UsrRepository usrRepository;

    public Order saveOrder(OrderSaveRequest order, Long user_id) {
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

        Usr usr = null;
        try {
            usr = usrRepository.findById(user_id).orElse(null);
        } catch (Exception e) {
        }

        Order o = Order.builder()
                .ownerUserId(order.getOwnerUserId())
                .loadingPoint(loadingPoint)
                .destinationPoint(destinationPoint)
                .volume(order.getVolume())
                .weight(order.getWeight())
                .expirationDate(order.getExpirationDate())
                .usr(usr)
                .build();

        return orderRepository.save(o);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
