package ru.hackaton.logistic.request;

import lombok.Data;

@Data
public class GetRouteListForOrderRequest {
    private Long orderId;
}
