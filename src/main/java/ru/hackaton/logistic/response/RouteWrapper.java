package ru.hackaton.logistic.response;

import lombok.Data;
import ru.hackaton.logistic.domain.Route;

@Data
public class RouteWrapper extends Route{
    private Long acceptedCount;
    private Double acceptedVolume;
    private Double acceptedWeight;

    public RouteWrapper(Route route){
        setId(route.getId());
        setLoadingPoint(route.getLoadingPoint());
        setDestinationPoint(route.getDestinationPoint());
        setCar(route.getCar());
        setIsOpen(route.getIsOpen());
        setIsStraight(route.getIsStraight());
        setDeliveryRadius(route.getDeliveryRadius());
        setCostKg(route.getCostKg());
        setCostLt(route.getCostLt());
        setUsr(route.getUsr());
    }

}
