package com.java.course.spring.data.car_engine.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user1")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User1Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @ManyToMany(fetch = FetchType.EAGER, cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_buildings",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "building_id", referencedColumnName = "building_id"
            )
    )
    private Set<Building1Entity> buildings = new HashSet<>();

}
