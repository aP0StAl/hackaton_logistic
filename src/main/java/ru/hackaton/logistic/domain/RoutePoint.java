package ru.hackaton.logistic.domain;

import lombok.Data;
import ru.hackaton.logistic.utils.RandomString;
import ru.hackaton.logistic.utils.Utils;

import java.util.List;

@Data
public class RoutePoint {
    private GeoPoint geoPoint;
    private String name;
    private List<Order> orders;
    private String action;
    private Double volume;
    private Double weight;

    public static RoutePoint getRandom(String action, List<Order> orders){
        RoutePoint result = new RoutePoint();
        result.setGeoPoint(new GeoPoint(
                Utils.random(45.040901, 45.138632),
                Utils.random(38.920087, 39.090288)));
        result.setOrders(orders);
        result.setAction(action);
        result.setVolume(Utils.round(orders.stream().mapToDouble(Order::getVolume).sum(), 2));
        result.setWeight(Utils.round(orders.stream().mapToDouble(Order::getWeight).sum(), 2));
        result.setName("Название склада "+ RandomString.randomString(5,RandomString.digits));
        return result;
    }
}

