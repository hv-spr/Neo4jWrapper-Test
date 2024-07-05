package com.example.neo4j_test.customQueryClasses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonNodeEntity{
    private String categoryId;
    private String categoryName;
    private String description;
}