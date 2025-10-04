package br.com.michael_fausto.formulaSAE.model.car.brakes;

public record BrakeData(
        Integer discTemperature, //Celsius
        Integer fluidPressure, //Bar
        Boolean handBrake
        ){}
