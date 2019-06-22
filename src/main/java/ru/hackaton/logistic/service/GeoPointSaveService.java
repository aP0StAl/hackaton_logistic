package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.GeoPoint;
import ru.hackaton.logistic.repository.GeoPointRepository;
import ru.hackaton.logistic.request.GeoPointSaveRequest;

@Service
@RequiredArgsConstructor
public class GeoPointSaveService {
    private final GeoPointRepository geoPointRepository;

    public GeoPoint saveGeopint(GeoPointSaveRequest geoPointSaveRequest){

        GeoPoint geoPoint = GeoPoint.builder()
                .name(geoPointSaveRequest.getName())
                .address(geoPointSaveRequest.getAddress())
                .lat(geoPointSaveRequest.getLat())
                .lon(geoPointSaveRequest.getLon())
                .build();

        return geoPointRepository.save(geoPoint);

    }

}
