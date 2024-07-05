package com.example.neo4j_test.customQueryClasses;

import com.example.neo4j_test.entities.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetail {
    private CustomerEntity customerEntity;
    private String customerID;
    private String contactName;
    private String orderID;
    private String orderDate;
    private String productName;
    private Long quantity;
}