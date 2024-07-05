package com.example.neo4j_test;

import com.sprinklr.neo4j.Neo4jWrapper;

import com.example.neo4j_test.customQueryClasses.ExampleComplexAggregate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ComplexAggregationTest {

    @Autowired
    private Neo4jWrapper neo4jWrapper;

    @Test
    @Transactional
    public void aggregateOrderWithCollectProduct() {
        List<ExampleComplexAggregate> exampleAggregates = neo4jWrapper.executeQuery("MATCH (o:Order where o.orderID = \"10248\")-[:ORDERS]->(p:Product) RETURN o as orderEntity, collect(p) as productEntities, count(p) as productCount", ExampleComplexAggregate.class);

        for (ExampleComplexAggregate exampleAggregate : exampleAggregates) {
            assertThat(exampleAggregate.getOrderEntity().getOrderID()).isNotNull();
            assertThat(exampleAggregate.getProductCount()).isEqualTo(exampleAggregate.getProductEntities().size());
            exampleAggregate.getProductEntities().forEach(productEntity -> assertThat(productEntity.getProductID()).isNotNull());
        }
    }
}
