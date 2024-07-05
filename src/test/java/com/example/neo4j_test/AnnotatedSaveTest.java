package com.example.neo4j_test;

import com.sprinklr.neo4j.Neo4jWrapper;
import com.example.neo4j_test.entities.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AnnotatedSaveTest {

    @Autowired
    private Neo4jWrapper neo4jWrapper;

    @Test
    @Transactional
    public void saveEmployee() {
        EmployeeEntity employee1 = new EmployeeEntity();
        employee1.setEmployeeID("temp");
        employee1.setFirstName("temp_first");
        employee1.setLastName("temp_last");

        neo4jWrapper.save(employee1);

        List<EmployeeEntity> queried_employee = neo4jWrapper.executeQuery("MATCH (e:Employee where e.employeeID = \"temp\") RETURN e;", EmployeeEntity.class);
        assertThat((long) queried_employee.size()).isEqualTo(1);
        assertThat(queried_employee.get(0).getEmployeeID()).isNotNull();
        assertThat(queried_employee.get(0).getEmployeeID()).isEqualTo("temp");
    }
}
