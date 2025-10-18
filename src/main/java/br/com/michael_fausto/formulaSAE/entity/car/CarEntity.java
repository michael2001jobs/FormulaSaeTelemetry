package br.com.michael_fausto.formulaSAE.entity.car;

import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeSetupEntity;
import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingSetupEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @OneToOne
    @JoinColumn(name = "brake_setup_id")
    BrakeSetupEntity brakeSetup;

    @OneToOne
    @JoinColumn(name = "cooling_setup_id")
    CoolingSetupEntity coolingSetup;

}
