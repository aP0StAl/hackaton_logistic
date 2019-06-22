package ru.hackaton.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.logistic.domain.Order;
import ru.hackaton.logistic.domain.Usr;

import java.util.ArrayList;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long>{
}
