package ru.hackaton.logistic.request;

import lombok.Data;
import ru.hackaton.logistic.domain.enums.OrderJoinStatus;

@Data
public class SetJoinStatusRequest {
    private Long orderId;
    private OrderJoinStatus joinStatus;
}
