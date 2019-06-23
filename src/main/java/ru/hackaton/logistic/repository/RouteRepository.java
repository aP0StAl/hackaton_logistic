package ru.hackaton.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.logistic.domain.Route;

import java.util.ArrayList;

public interface RouteRepository extends JpaRepository<Route, Long> {
    ArrayList<Route> findAllByIsOpen(Boolean is);
}
