package com.example.neo4j_test;

import com.sprinklr.neo4j.Neo4jWrapper;

import com.example.neo4j_test.entities.RegionEntity;
import com.example.neo4j_test.customQueryClasses.ExtendedRegionEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NonReturnQueryTest {

    @Autowired
    private Neo4jWrapper neo4jWrapper;

    @Test
    @Transactional
    public void queryWithNoReturn() {
        neo4jWrapper.save(new RegionEntity("temp_id", "temp_description"));
        neo4jWrapper.executeQuery("MATCH (r:Region where r.regionID = \"temp_id\") SET r.created_prop = 1;");
        ExtendedRegionEntity test_region = neo4jWrapper.executeQuery("MATCH (r:Region where r.regionID = \"temp_id\") RETURN r as regionEntity, r.created_prop as created_prop;", ExtendedRegionEntity.class).get(0);
        assertThat(test_region.getRegionEntity().getRegionID()).isEqualTo("temp_id");
        assertThat(test_region.getCreated_prop()).isEqualTo(1);
    }
}
