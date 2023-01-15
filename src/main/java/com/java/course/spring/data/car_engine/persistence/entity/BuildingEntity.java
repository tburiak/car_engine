package com.java.course.spring.data.car_engine.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "building")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private int id;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "building")
    Set<UserRegistrationEntity> registrations;
}
