package ru.hackaton.logistic.domain;

import lombok.*;
import org.hibernate.annotations.GeneratorType;
import ru.hackaton.logistic.response.RouteWrapper;
import ru.hackaton.logistic.utils.RandomString;
import ru.hackaton.logistic.utils.Utils;

import javax.persistence.*;

import static ru.hackaton.logistic.utils.RandomString.randomString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //Айди маршрута

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="loading_point_id")
    private GeoPoint loadingPoint; //Точка А

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="destination_point_id")
    private GeoPoint destinationPoint; //Точка В

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="car_id")
    private Car car; //Машина для маршрута

    private Boolean isOpen; //Является ли маршрут открытым для добавления чужих заявок
    private Boolean isStraight; //1, если маршрут прямой, 0 - если маршрут с довозом
    private Double deliveryRadius; //Радиус возможной доставки

    private Double costKg; //Стоимость перевозки за кг в руб
    private Double costLt; //Стоимость перевозки за литр в руб

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="usr_id")
    private Usr usr;

}
