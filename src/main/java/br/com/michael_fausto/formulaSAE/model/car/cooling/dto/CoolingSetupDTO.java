package br.com.michael_fausto.formulaSAE.model.car.cooling.dto;

import java.time.LocalDateTime;

public record CoolingSetupDTO (

    String name,
    LocalDateTime createdIn,

    Integer normalCoolingTemperature,
    Integer highCoolingTemperature,

    Double lowReservoirVolume,
    Double normalReservoirVolume){}
