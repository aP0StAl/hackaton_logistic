package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.Car;
import ru.hackaton.logistic.repository.CarRepository;
import ru.hackaton.logistic.request.CarSaveRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public Car saveCar(CarSaveRequest car) {
        Car c = Car.builder()
                .maxWeight(car.getMaxWeight())
                .maxVolume(car.getMaxVolume())
                .build();

        return carRepository.save(c);
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }
}
