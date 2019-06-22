package ru.hackaton.logistic.domain;

import lombok.Data;
import ru.hackaton.logistic.utils.RandomString;
import ru.hackaton.logistic.utils.Utils;

import static ru.hackaton.logistic.utils.RandomString.randomString;

@Data
public class Order {
    private String name;
    private String product;
    private Double volume;
    private Double weight;

    public static Order getRandom(){
        Order result = new Order();
        result.setName(randomString(3, RandomString.upper) + randomString(10, RandomString.digits));
        result.setProduct("Название продукта "+randomString(5, RandomString.digits));
        result.setVolume(Utils.round(Math.random() * 15, 2));
        result.setWeight(Utils.round(Math.random() * 7, 2));
        return result;
    }
}
