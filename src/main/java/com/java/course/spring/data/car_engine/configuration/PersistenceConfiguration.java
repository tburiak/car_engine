package com.java.course.spring.data.car_engine.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.java.course.spring.data.car_engine.persistence.repository")
@EntityScan(basePackages = "com.java.course.spring.data.car_engine.persistence.entity")
public class PersistenceConfiguration {
}
