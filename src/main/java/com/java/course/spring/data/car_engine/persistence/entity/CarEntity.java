package com.java.course.spring.data.car_engine.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity @Table(name = "car")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;
    private String title;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval=true )
    @JoinColumn(name = "car_engine_id")
    private EngineEntity engineEntity;
}
