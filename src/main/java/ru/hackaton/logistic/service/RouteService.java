package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.*;
import ru.hackaton.logistic.repository.CarRepository;
import ru.hackaton.logistic.repository.RouteRepository;
import ru.hackaton.logistic.repository.UsrRepository;
import ru.hackaton.logistic.request.RouteSaveRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final CarRepository carRepository;
    private final UsrRepository usrRepository;

    public Route saveRoute(RouteSaveRequest route, Long userId) {

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

        Car car = null;
        if(route.getCarId() != null) {
            car = carRepository.findById(route.getCarId()).orElse(null);
        }

        Usr usr = null;
        try {
            usr = usrRepository.findById(userId).orElse(null);
        } catch (Exception e) {
        }

        Route r = Route.builder()
                .loadingPoint(loadingPoint)
                .destinationPoint(destinationPoint)
                .car(car)
                .isOpen(route.getIsOpen())
                .isStraight(route.getIsStraight())
                .deliveryRadius(route.getDeliveryRadius())
                .costKg(route.getCostKg())
                .costLt(route.getCostLt())
                .usr(usr)
                .build();

        return routeRepository.save(r);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public List<Route> getAllRoutes(Long userId) {
        List<Route> routes = getAllRoutes();
        return routes.stream().filter(o -> o.getUsr() != null && userId.equals(o.getUsr().getId())).collect(Collectors.toList());
    }

}
