package br.com.michael_fausto.formulaSAE.entity.car.brake;

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

}
