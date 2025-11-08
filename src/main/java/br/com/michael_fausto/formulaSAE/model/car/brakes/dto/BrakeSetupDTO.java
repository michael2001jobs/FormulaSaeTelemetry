package br.com.michael_fausto.formulaSAE.model.car.brakes.dto;

import java.time.LocalDateTime;

public record BrakeSetupDTO(

        Long id,

        String name,
        LocalDateTime createAt,

        Integer normalDiscTemperature,
        Integer highDiscTemperature,

        Integer normalFluidPressure,
        Integer highFluidPressure) {}
