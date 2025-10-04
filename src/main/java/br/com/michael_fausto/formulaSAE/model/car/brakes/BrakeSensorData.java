package br.com.michael_fausto.formulaSAE.model.car.brakes;

public record BrakeSensorData(
        String discBreakTemperature, //Celsius
        String brakeFluidPressure, //Bar
        String handBrake
        ) {}
