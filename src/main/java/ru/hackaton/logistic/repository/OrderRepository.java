package ru.hackaton.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.logistic.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
