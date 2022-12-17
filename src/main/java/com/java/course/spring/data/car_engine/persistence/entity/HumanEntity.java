package com.java.course.spring.data.car_engine.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "human")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HumanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "human_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @OneToMany(mappedBy = "human", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarEntity> carEntities;

}
