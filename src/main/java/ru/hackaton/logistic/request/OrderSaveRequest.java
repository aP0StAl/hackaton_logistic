package ru.hackaton.logistic.request;

import lombok.Data;

@Data
public class OrderSaveRequest {
    private String loadingPointName;
    private String destinationPointName;
}
