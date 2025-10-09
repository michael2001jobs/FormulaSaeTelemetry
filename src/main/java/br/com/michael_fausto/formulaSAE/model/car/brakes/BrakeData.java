package br.com.michael_fausto.formulaSAE.model.car.brakes;

public record BrakeData(
        String wheelPosition, //Ex: "FL", "FR", "RL", "RR"
        Integer discTemperature, //Celsius
        Integer fluidPressure //Bar
        ){}
