package com.example.neo4j_test.customQueryClasses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetail {
    private String productID;
    private String productName;
    private Long count;
}
