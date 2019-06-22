package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.Car;
import ru.hackaton.logistic.domain.GeoPoint;
import ru.hackaton.logistic.domain.Route;
import ru.hackaton.logistic.repository.CarRepository;
import ru.hackaton.logistic.repository.RouteRepository;
import ru.hackaton.logistic.request.RouteSaveRequest;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final CarRepository carRepository;

    public Route saveRoute(RouteSaveRequest route) {

        GeoPoint loadingPoint = GeoPoint.builder()
                .name(route.getFromTitle())
                .lat(route.getFromPoint().get(0))
                .lon(route.getFromPoint().get(1))
                .build();

        GeoPoint destinationPoint = GeoPoint.builder()
                .name(route.getToTitle())
                .lat(route.getToPoint().get(0))
                .lon(route.getToPoint().get(1))
                .build();

        Car car = carRepository.findById(route.getCarId()).orElse(null);

        Route r = Route.builder()
                .loadingPoint(loadingPoint)
                .destinationPoint(destinationPoint)
                .car(car)
                .ownerUserId(route.getOwnerUserid())
                .isOpen(route.getIsOpen())
                .isStraight(route.getIsStraight())
                .deliveryRadius(route.getDeliveryRadius())
                .costKg(route.getCostKg())
                .costLt(route.getCostLt())
                .build();

        return routeRepository.save(r);
    }
}
