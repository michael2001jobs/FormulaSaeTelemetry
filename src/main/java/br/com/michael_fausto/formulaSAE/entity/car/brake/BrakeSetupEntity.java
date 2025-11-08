package br.com.michael_fausto.formulaSAE.entity.car.brake;

import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeSetupDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brake_setups")
public class BrakeSetupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "system_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createAt;



    @Column(name = "normal_limit_temp", nullable = false)
    private Integer normalDiscTemperature;

    @Column(name = "hight_limit_temp", nullable = false)
    private Integer highDiscTemperature;



    @Column(name = "normal_fluid_press", nullable = false)
    private Integer normalFluidPressure;

    @Column(name = "high_fluid_press", nullable = false)
    private Integer highFluidPressure;

    public BrakeSetupEntity(BrakeSetupDTO dto) {
        this.name = dto.name();
        this.createAt = LocalDateTime.now();
        this.normalDiscTemperature = dto.normalDiscTemperature();
        this.highDiscTemperature = dto.highDiscTemperature();
        this.normalFluidPressure = dto.normalFluidPressure();
        this.highFluidPressure = dto.highFluidPressure();
    }
}
