package ru.hackaton.logistic.request;

import lombok.Data;

@Data
public class OrderRouteJoinRequest {
    private Long orderId;
    private Long routeId;
}
