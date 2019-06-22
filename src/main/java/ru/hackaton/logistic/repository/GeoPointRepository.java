package ru.hackaton.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.logistic.domain.GeoPoint;

public interface GeoPointRepository extends JpaRepository<GeoPoint, Long> {
}
