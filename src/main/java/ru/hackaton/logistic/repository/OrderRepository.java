package ru.hackaton.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.logistic.domain.Order;

import java.util.ArrayList;


public interface OrderRepository extends JpaRepository<Order, Long>{
    ArrayList<Order> findAllByOwnerUserId(Long id);
}
