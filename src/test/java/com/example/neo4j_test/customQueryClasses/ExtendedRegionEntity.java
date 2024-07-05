package com.example.neo4j_test.customQueryClasses;

import com.example.neo4j_test.entities.RegionEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtendedRegionEntity{
    private RegionEntity regionEntity;
    private Long created_prop;
}