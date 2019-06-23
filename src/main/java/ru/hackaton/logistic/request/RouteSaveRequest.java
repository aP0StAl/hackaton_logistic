package ru.hackaton.logistic.request;

import lombok.Data;

import java.util.List;

@Data
public class RouteSaveRequest {
    private List<Double> fromPoint; //Точка A
    private String fromTitle;

    private List<Double> toPoint; //Точка В
    private String toTitle;

    private Long carId;
    private Long ownerUserId;

    private Boolean isOpen; //Является ли маршрут открытым для добавления чужих заявок
    private Boolean isStraight; //1, если маршрут прямой, 0 - если маршрут с довозом
    private Double deliveryRadius = 300.0; //Радиус возможной доставки

    private Double costKg; //Стоимость перевозки за кг в руб
    private Double costLt; //Стоимость перевозки за литр в руб
}
