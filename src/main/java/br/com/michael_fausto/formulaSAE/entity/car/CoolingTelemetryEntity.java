package br.com.michael_fausto.formulaSAE.entity.car;

import br.com.michael_fausto.formulaSAE.model.car.ComponentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cooling_telemetry")
public class CoolingTelemetryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private LocalDateTime timestamp;

    @Column(name = "fan",nullable = false)
    private Boolean fan;

    public CoolingTelemetryEntity() {
    }

    public CoolingTelemetryEntity(Long id, Integer coolingSystemTemperature, ComponentStatus coolingSystemStatus, Double reservoirVolume, ComponentStatus reservoirVolumeStatus, LocalDateTime timestamp, Boolean fan) {
        this.id = id;
        this.coolingSystemTemperature = coolingSystemTemperature;
        this.coolingSystemStatus = coolingSystemStatus;
        this.reservoirVolume = reservoirVolume;
        this.reservoirVolumeStatus = reservoirVolumeStatus;
        this.timestamp = timestamp;
        this.fan = fan;
    }

}
