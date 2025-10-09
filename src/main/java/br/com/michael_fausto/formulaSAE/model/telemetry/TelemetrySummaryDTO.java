package br.com.michael_fausto.formulaSAE.model.telemetry;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TelemetrySummaryDTO(
        Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp
) {

}
