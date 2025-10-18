package br.com.michael_fausto.formulaSAE.model.car;

import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeSetupDTO;
import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingSetupDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record CarDTO(
        @JsonIgnore
        Long id,
        String name,
        BrakeSetupDTO brakeSetup,
        CoolingSetupDTO coolingSetup
) {}
