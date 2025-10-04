package br.com.michael_fausto.formulaSAE.model.car.eletric;

import br.com.michael_fausto.formulaSAE.util.ConvertUtills;

public class EletricalParse {

    public static ElectricalData eletricalParse(EletricalSensorData eletricalSensorData) {
        return new ElectricalData(
                ConvertUtills.doubleConvert(eletricalSensorData.batteryVoltage()),
                ConvertUtills.doubleConvert(eletricalSensorData.batteryCurrent())
        );
    }
}
