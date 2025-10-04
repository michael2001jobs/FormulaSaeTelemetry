package br.com.michael_fausto.formulaSAE.model.car.motor;

import br.com.michael_fausto.formulaSAE.util.ConvertUtills;

public class MotorParse {

    public static MotorData motorParse(MotorSensorData motorSensorData) {
        return new MotorData(
                ConvertUtills.intConvert(motorSensorData.motorTemperature()),
                ConvertUtills.intConvert(motorSensorData.rpm()),
                ConvertUtills.intConvert(motorSensorData.speed())
        );
    }
}
