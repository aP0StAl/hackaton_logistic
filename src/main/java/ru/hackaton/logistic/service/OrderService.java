package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public Order saveOrder(Order order) {
        try {
            return repository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
