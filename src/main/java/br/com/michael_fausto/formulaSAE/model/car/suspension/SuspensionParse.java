package br.com.michael_fausto.formulaSAE.model.car.suspension;

import br.com.michael_fausto.formulaSAE.util.ConvertUtills;

public class SuspensionParse {

    public static SuspensionData suspensionParse(SuspensionSensorData suspensionSensorData) {
        return new SuspensionData(
                ConvertUtills.intConvert(suspensionSensorData.steeringAngle()),
                ConvertUtills.intConvert(suspensionSensorData.vehicleInclination())
        );
    }
}
