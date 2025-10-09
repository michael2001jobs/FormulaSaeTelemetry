package br.com.michael_fausto.formulaSAE.model.car.brakes;

public record BrakeSensorData(
        String wheelPosition, //Which of the 4 brakes
        String discBreakTemperature, //Celsius
        String brakeFluidPressure//Bar
        ) {}
