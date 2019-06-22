package ru.hackaton.logistic.request;

import lombok.Data;

@Data
public class CarSaveRequest {
    private Long ownerId;
    private Double maxVolume;
    private Double maxWeight;
}
