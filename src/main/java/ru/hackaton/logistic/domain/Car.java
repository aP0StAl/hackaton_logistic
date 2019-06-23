package ru.hackaton.logistic.domain;

import lombok.*;
import ru.hackaton.logistic.utils.RandomString;
import ru.hackaton.logistic.utils.Utils;

import javax.persistence.*;

import java.time.LocalDateTime;

import static ru.hackaton.logistic.utils.RandomString.randomString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double maxWeight;
    private Double maxVolume;
    private String name;
}
