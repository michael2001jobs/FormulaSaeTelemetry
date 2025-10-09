package br.com.michael_fausto.formulaSAE.model.telemetry;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeDTO;
import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

public record TelemetryDTO(
        @JsonIgnore
        Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        List<BrakeDTO> brakeTelemetry,
        List<CoolingDTO> coolingTelemetry) {}
