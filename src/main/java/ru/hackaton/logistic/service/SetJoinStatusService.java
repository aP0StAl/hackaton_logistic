package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.repository.OrderRepository;
import ru.hackaton.logistic.request.SetJoinStatusRequest;

@Service
@RequiredArgsConstructor
public class SetJoinStatusService {
    private final OrderRepository orderRepository;

    public void setStatus(SetJoinStatusRequest req) {
        Order ord = orderRepository.findById(req.getOrderId()).orElse(null);
        ord.setJoinStatus(req.getJoinStatus());
        orderRepository.save(ord);
    }
}
