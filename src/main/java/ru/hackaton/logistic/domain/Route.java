package ru.hackaton.logistic.domain;

import lombok.Data;
import ru.hackaton.logistic.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@Data
public class Route {
    private List<RoutePoint> routePoints;
    private double weight;
    private double volume;
    private double loadingPointsCount;
    private double unloadingPointsCount;
    private double distance;

    public static Route getRandom(){
        Route result = new Route();
        result.setLoadingPointsCount(1);
        result.setUnloadingPointsCount(Utils.randint(6)+1);
        List<Order> ords = new ArrayList<>();
        for(int i=0;i<result.getUnloadingPointsCount();i++){
            ords.add(Order.getRandom());
        }
        List<RoutePoint> rps = new ArrayList<>();
        rps.add(RoutePoint.getRandom("Загрузка", ords));
        for(int i=ords.size()-1;i>=0;i--){
            Order order = ords.get(i);
            List<Order> lst = new ArrayList<>();
            lst.add(order);
            rps.add(RoutePoint.getRandom("Разгрузка", lst));
        }
        result.setRoutePoints(rps);
        result.setVolume(Utils.round(ords.stream().mapToDouble(Order::getVolume).sum(), 2));
        result.setWeight(Utils.round(ords.stream().mapToDouble(Order::getWeight).sum(), 2));
        result.setDistance(Utils.round(Utils.random(30, 70), 2));
        return result;
    }
}
