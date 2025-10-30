package br.com.michael_fausto.formulaSAE.model.car.brakes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public record BrakeSetupDTO(

        String name,
        LocalDateTime createAt,

        Integer normalDiscTemperature,
        Integer highDiscTemperature,

        Integer normalFluidPressure,
        Integer highFluidPressure) {}
