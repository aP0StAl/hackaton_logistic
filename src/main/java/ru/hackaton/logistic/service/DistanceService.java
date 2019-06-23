package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.GeoPoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DistanceService {
    public double getDistance(GeoPoint a, GeoPoint b){
        return distOnSphere(a, b);
    }

    private double distOnSphere(GeoPoint a, GeoPoint b) {
        double ac = Math.acos(Math.sin(a.getLat()) * Math.sin(b.getLat()) +
                Math.cos(a.getLat()) * Math.cos(b.getLat()) * Math.cos(a.getLon() - b.getLon()));
        return ac * 6371;
    }

    public Map<GeoPoint, Map<GeoPoint, Double>> getDistanceMap(List<GeoPoint> points){
        Map<GeoPoint, Map<GeoPoint, Double>> distanceMap = new HashMap<>();
        for (GeoPoint p1 : points) {
            Map<GeoPoint, Double> subMap = new HashMap<>();
            for (GeoPoint p2: points){
                subMap.put(p2, getDistance(p1, p2));
            }
            distanceMap.put(p1, subMap);
        }
        return distanceMap;
    }
}
