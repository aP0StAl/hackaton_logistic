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
        return distFrom(a.getLat(), a.getLon(), b.getLat(), b.getLon());
    }

    private double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return   earthRadius * c;
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
