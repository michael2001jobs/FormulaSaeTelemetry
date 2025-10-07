package br.com.michael_fausto.formulaSAE.entity.car;

import br.com.michael_fausto.formulaSAE.model.car.ComponentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table (name = "brake_telemetry")
@AllArgsConstructor
@NoArgsConstructor
public class BrakeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @Column(name = "handbrake", nullable = false)
    private Boolean handBrake;

}
