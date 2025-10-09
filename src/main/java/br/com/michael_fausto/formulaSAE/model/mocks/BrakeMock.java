package br.com.michael_fausto.formulaSAE.model.mocks;

import br.com.michael_fausto.formulaSAE.model.car.brakes.BrakeSensorData;

import java.util.Random;

public class BrakeMock {

    public static BrakeSensorData brakeMockSimulationArduino() {
        Random random = new Random();
        Integer discBreakTemperature = 100 + random.nextInt(350);
        Integer brakeFluidPressure = 5 + random.nextInt(50);

        return new BrakeSensorData(
                "MOCK_POSITION",
                String.valueOf(discBreakTemperature),
                String.valueOf(brakeFluidPressure)
        );
    }
}
