package br.com.michael_fausto.formulaSAE.entity.car.cooling;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cooling_setup")
public class CoolingSetupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "profile_name", nullable = false)
    String profileName;


    @Column(name = "normal_cooling_temp", nullable = false)
    private Integer normalCoolingTemperature;

    @Column(name = "high_cooling_temp", nullable = false)
    private Integer highCoolingTemperature;


    @Column(name = "low_reservoir_volume", nullable = false)
    private Double lowReservoirVolume;

    @Column(name = "normal_reservoir_volume", nullable = false)
    private Double normalReservoirVolume;


}
