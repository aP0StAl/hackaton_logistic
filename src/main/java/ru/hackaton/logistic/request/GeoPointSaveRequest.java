package ru.hackaton.logistic.request;

import lombok.Data;

@Data
public class GeoPointSaveRequest {
    public String name;
    public String address;
    public Double lat;
    public Double lon;
}
