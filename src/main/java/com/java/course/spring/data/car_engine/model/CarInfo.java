package com.java.course.spring.data.car_engine.model;

import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import com.java.course.spring.data.car_engine.persistence.entity.EngineEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CarInfo {
    private CarEntity carEntity;
    private String engineTitle;
}
