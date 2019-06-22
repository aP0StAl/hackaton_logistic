package ru.hackaton.logistic.request;

import lombok.Data;

@Data
public class GetOrderListRequest {
    private Long ownerUserId;
}
