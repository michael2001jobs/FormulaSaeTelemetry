package br.com.michael_fausto.formulaSAE.entity.car.cooling;

import br.com.michael_fausto.formulaSAE.model.car.ComponentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cooling_telemetry")
public class CoolingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "cooling_temperature", nullable = true)
    private Integer coolingSystemTemperature;

    @Column(name = "cooling_status", nullable = true)
    @Enumerated(EnumType.STRING)
    private ComponentStatus coolingSystemStatus;

    @Column(name = "reservoir_volume", nullable = false)
    private Double reservoirVolume;

    @Column(name = "reservoir_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ComponentStatus reservoirVolumeStatus;

    @Column(name = "timestamp", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @Column(name = "fan",nullable = false)
    private Boolean fan;

}
