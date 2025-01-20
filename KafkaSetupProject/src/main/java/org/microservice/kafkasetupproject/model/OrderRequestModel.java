package org.microservice.kafkasetupproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestModel {
    private Long id;
    private String orderId;
    private String customerName;
    private Double amount;

    @Override
    public String toString(){
        return "OrderRequestModel{" +
                "id='"+ id
                + ",orderId='"+ orderId+ '\''+
                ",customerName='"+ customerName +'\''+
                ", amount="+ amount+ '}';
    }
}