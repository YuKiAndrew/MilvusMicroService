package com.ai.boost.model;

import lombok.Data;

import java.util.List;

@Data
public class CarModel {

    private long carId;

    private String carModel;

    private String carMake;

    private List<Float> carVector;

}
