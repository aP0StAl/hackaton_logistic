package ru.hackaton.logistic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hackaton.logistic.domain.enums.OrderJoinStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ownerUserId;
    private String name;
    private String product;
    private Double volume;
    private Double weight;
    private LocalDate expirationDate;

    private OrderJoinStatus joinStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="loading_point_id")
    private GeoPoint loadingPoint;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="destination_point_id")
    private GeoPoint destinationPoint;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="usr_id")
    private Usr usr;
}
