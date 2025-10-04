package br.com.michael_fausto.formulaSAE.model.car.cooling;

public record CoolingSensorData(String coolingSystemTemperature,
                                String reservoirVolume,
                                String fan) {}
