package br.com.michael_fausto.formulaSAE.model.car.cooling.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record CoolingSetupDTO (
    Long id,

    String profileName,

    Integer normalCoolingTemperature,
    Integer highCoolingTemperature,

    Double lowReservoirVolume,
    Double normalReservoirVolume){}
