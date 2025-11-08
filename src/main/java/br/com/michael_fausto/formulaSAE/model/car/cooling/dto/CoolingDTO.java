package br.com.michael_fausto.formulaSAE.model.car.cooling.dto;

import br.com.michael_fausto.formulaSAE.model.car.enums.ComponentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CoolingDTO(
        Integer coolingSystemTemperature,
        ComponentStatus coolingSystemStatus,
        Double reservoirVolume,
        ComponentStatus reservoirVolumeStatus,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        Boolean fan) {}
