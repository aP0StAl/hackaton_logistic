package ru.hackaton.logistic.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderSaveRequest {
    private Long ownerUserId;
    private List<Double> fromPoint;
    private String fromTitle;
    private List<Double> toPoint;
    private String toTitle;
    private Double volume;
    private Double weight;
    private LocalDate expirationDate;
}
