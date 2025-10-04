package br.com.michael_fausto.formulaSAE.model.mocks;

import br.com.michael_fausto.formulaSAE.model.car.brakes.BrakeSensorData;

import java.util.Random;

public class BrakeMock {

    public static BrakeSensorData brakeMockSimulatadArduino() {
        Random random = new Random();
        Integer discBreakTemperature = 100 + random.nextInt(350);
        Integer brakeFluidPressure = 5 + random.nextInt(50);
        Boolean handBreak = random.nextBoolean();

        return new BrakeSensorData(
                String.valueOf(discBreakTemperature),
                String.valueOf(brakeFluidPressure),
                String.valueOf(handBreak)
        );
    }
}
