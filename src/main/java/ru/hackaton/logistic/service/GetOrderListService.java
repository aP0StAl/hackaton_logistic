package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.repository.OrderRepository;
import ru.hackaton.logistic.request.GetOrderListRequest;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GetOrderListService {
    private final OrderRepository orderRepository;

    public ArrayList<Order> getList(GetOrderListRequest req) {
        return orderRepository.findAllByOwnerUserId(req.getOwnerUserId());
    }
}
