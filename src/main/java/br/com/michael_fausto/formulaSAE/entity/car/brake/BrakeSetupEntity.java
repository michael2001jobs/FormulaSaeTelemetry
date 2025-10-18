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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_system_name", nullable = false)
    private String profileName;




    @Column(name = "normal_limit_temp", nullable = false)
    private Integer normalDiscTemperature;

    @Column(name = "hight_limit_temp", nullable = false)
    private Integer highDiscTemperature;




    @Column(name = "normal_fluid_press", nullable = false)
    private Integer normalFluidPressure;

    @Column(name = "high_fluid_press", nullable = false)
    private Integer highFluidPressure;
}
