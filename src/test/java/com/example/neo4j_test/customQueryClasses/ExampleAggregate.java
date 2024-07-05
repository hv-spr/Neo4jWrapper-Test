package com.example.neo4j_test.customQueryClasses;

import com.example.neo4j_test.entities.OrderEntity;
import com.example.neo4j_test.entities.ProductEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExampleAggregate {
    private OrderEntity orderEntity;
    private ProductEntity productEntity;
}