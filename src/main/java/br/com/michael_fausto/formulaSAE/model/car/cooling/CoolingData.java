package br.com.michael_fausto.formulaSAE.model.car.cooling;

public record CoolingData(Integer coolingSystemTemperature,
                          Double reservoirVolume,
                          Boolean fan) {}
