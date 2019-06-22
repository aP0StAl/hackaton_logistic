package ru.hackaton.logistic.domain;

import lombok.Data;

import java.util.List;

@Data
public class RouteMap {
    private List<GeoPoint> points;
    private String distance;
    private String time;
    private GeoPoint center;


    public RouteMap(List<GeoPoint> points, String distance, String time) {
        this.points = points;
        this.distance = distance;
        this.time = time;

        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;

        for(int i = 0; i < points.size(); i++) {
            GeoPoint p = points.get(i);
            maxX = Math.max(maxX, p.getLat());
            maxY = Math.max(maxY, p.getLon());
            minX = Math.min(minX, p.getLat());
            minY = Math.min(minY, p.getLon());
        }

        this.center = new GeoPoint((maxX + minX) / 2, (maxY + minY) / 2);
    }
}
