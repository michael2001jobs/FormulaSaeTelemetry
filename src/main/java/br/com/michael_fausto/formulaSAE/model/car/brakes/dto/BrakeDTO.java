package br.com.michael_fausto.formulaSAE.model.car.brakes.dto;

import br.com.michael_fausto.formulaSAE.model.car.ComponentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record BrakeDTO(
        String wheelPosition,
        Integer discTemperature,
        ComponentStatus discTemperatureStatus,
        Integer fluidPressure,
        ComponentStatus fluidPressureStatus,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp) {
}
