package ru.hackaton.logistic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hackaton.logistic.utils.RandomString;
import ru.hackaton.logistic.utils.Utils;

import javax.persistence.*;

import static ru.hackaton.logistic.utils.RandomString.randomString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String product;
    private Double volume;
    private Double weight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="loading_point_id")
    private GeoPoint loadingPoint;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="destination_point_id")
    private GeoPoint destinationPoint;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="usr_id")
    private Usr usr;

    public static Order getRandom(){
        Order result = new Order();
        result.setName(randomString(3, RandomString.upper) + randomString(10, RandomString.digits));
        result.setProduct("Название продукта "+randomString(5, RandomString.digits));
        result.setVolume(Utils.round(Math.random() * 15, 2));
        result.setWeight(Utils.round(Math.random() * 7, 2));
        return result;
    }
}
