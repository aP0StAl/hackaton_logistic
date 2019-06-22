package ru.hackaton.logistic.service;

import org.springframework.stereotype.Service;
import ru.hackaton.logistic.domain.GeoPoint;

@Service
public class DistanceService {
    public double getDistance(GeoPoint a, GeoPoint b){
        return distOnSphere(a, b);
    }

    private double distOnSphere(GeoPoint a, GeoPoint b) {
        double ac = Math.acos(Math.sin(a.getLat()) * Math.sin(b.getLat()) +
                Math.cos(a.getLat()) * Math.cos(b.getLat()) * Math.cos(a.getLon() - b.getLon()));
        return ac * 6371;
    }
}
