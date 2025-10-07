package br.com.michael_fausto.formulaSAE.model.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

public record TelemetryDTO(
        @JsonIgnore
        PilotDTO pilotDTO,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        List<BrakeDTO> brakeTelemetry,
        List<CoolingDTO> coolingTelemetry) {}
