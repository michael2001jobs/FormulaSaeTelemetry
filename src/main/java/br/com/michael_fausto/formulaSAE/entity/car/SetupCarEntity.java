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
public class SetupCarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    private BrakeSetupEntity brakeSetup;

    @OneToOne(fetch = FetchType.EAGER)
    private CoolingSetupEntity coolingSetup;

    public SetupCarEntity(String name) {
        this.name = name;
        this.id = null;
        this.coolingSetup = null;
        this.brakeSetup = null;
    }
}
