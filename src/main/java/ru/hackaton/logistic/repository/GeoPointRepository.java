package ru.hackaton.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.logistic.domain.GeoPoint;

import java.util.List;

public interface GeoPointRepository extends JpaRepository<GeoPoint, Long> {

}
