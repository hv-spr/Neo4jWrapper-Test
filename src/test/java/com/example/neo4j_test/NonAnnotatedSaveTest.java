package com.example.neo4j_test;

import com.sprinklr.neo4j.Neo4jWrapper;

import com.example.neo4j_test.customQueryClasses.NonNodeEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NonAnnotatedSaveTest {

    @Autowired
    private Neo4jWrapper neo4jWrapper;

    @Test
    @Transactional
    public void nonAnnotatedSave() {
        NonNodeEntity a = new NonNodeEntity();
        a.setCategoryId("non_node");
        a.setDescription("temp");
        a.setCategoryName("temp");

        neo4jWrapper.save(a);

        List<NonNodeEntity> queried_employee = neo4jWrapper.executeQuery("MATCH (e:NonNodeEntity where e.categoryId = \"non_node\") RETURN e;", NonNodeEntity.class);
        assertThat((long) queried_employee.size()).isEqualTo(1);
        assertThat(queried_employee.get(0).getCategoryId()).isNotNull();
        assertThat(queried_employee.get(0).getCategoryId()).isEqualTo("non_node");
    }
}
