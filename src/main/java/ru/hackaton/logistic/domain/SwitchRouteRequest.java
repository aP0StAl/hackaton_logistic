package ru.hackaton.logistic.domain;

import lombok.Data;

@Data
public class SwitchRouteRequest {
    private Boolean close;
    private Long routeId;
}
