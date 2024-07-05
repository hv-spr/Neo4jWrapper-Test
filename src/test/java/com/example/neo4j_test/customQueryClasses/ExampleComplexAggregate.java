package com.example.neo4j_test.customQueryClasses;

import com.example.neo4j_test.entities.OrderEntity;
import com.example.neo4j_test.entities.ProductEntity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExampleComplexAggregate {
    private OrderEntity orderEntity;
    private List<ProductEntity> productEntities;
    private Long productCount;
}
