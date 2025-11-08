package br.com.michael_fausto.formulaSAE.entity.car.cooling;

import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingSetupDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cooling_setup")
public class CoolingSetupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name = "profile_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdIn;



    @Column(name = "normal_cooling_temp", nullable = false)
    private Integer normalCoolingTemperature;

    @Column(name = "high_cooling_temp", nullable = false)
    private Integer highCoolingTemperature;



    @Column(name = "low_reservoir_volume", nullable = false)
    private Double lowReservoirVolume;

    @Column(name = "normal_reservoir_volume", nullable = false)
    private Double normalReservoirVolume;


    public CoolingSetupEntity(CoolingSetupDTO dto) {
        this.name = dto.name();
        this.createdIn = LocalDateTime.now();
        this.normalCoolingTemperature = dto.normalCoolingTemperature();
        this.highCoolingTemperature = dto.highCoolingTemperature();
        this.lowReservoirVolume = dto.lowReservoirVolume();
        this.normalReservoirVolume = dto.normalReservoirVolume();
    }

}
