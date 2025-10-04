package br.com.michael_fausto.formulaSAE.entity.car;

import br.com.michael_fausto.formulaSAE.entity.TelemetryDataEntity;
import br.com.michael_fausto.formulaSAE.model.car.ComponentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table (name = "break_telemetry")
public class BrakeTelemetryEntity extends TelemetryDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "disc_temperature", nullable = true)
    private Integer discTemperature;

    @Column(name = "disc_status", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private ComponentStatus discTemperatureStatus;

    @Column(name = "fluid_pressure", nullable = true)
    private Integer fluidPressure;

    @Column(name = "fluid_pressure_status", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private ComponentStatus fluidPressureStatus;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "handbrake", nullable = false)
    private Boolean handBrake;

    public BrakeTelemetryEntity(Long id,
                                Integer discTemperature,
                                ComponentStatus discTemperatureStatus,
                                Integer fluidPressure,
                                ComponentStatus fluidPressureStatus,
                                LocalDateTime timestamp,
                                Boolean handBrake) {
        this.id = id;
        this.discTemperature = discTemperature;
        this.discTemperatureStatus = discTemperatureStatus;
        this.fluidPressure = fluidPressure;
        this.fluidPressureStatus = fluidPressureStatus;
        this.timestamp = timestamp;
        this.handBrake = handBrake;
    }

    public BrakeTelemetryEntity() {
    }
}
