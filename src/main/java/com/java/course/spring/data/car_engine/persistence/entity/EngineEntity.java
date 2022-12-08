package com.java.course.spring.data.car_engine.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "engine")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EngineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "engine_id")
    private int id;
    private String title;

    public EngineEntity(String title) {
        this.title = title;
    }
}
