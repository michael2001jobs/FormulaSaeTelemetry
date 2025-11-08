package br.com.michael_fausto.formulaSAE.model.car;

import java.time.LocalDateTime;

public record CarDTO(

        String name,
        String versionOrModel,
        LocalDateTime createdIn,
        SetupCarDTO subSystemSetup) {}
