package com.example.neo4j_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sprinklr.neo4j"})
public class Neo4jTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(Neo4jTestApplication.class, args);
	}
}
