package br.com.michael_fausto.formulaSAE.model.mocks;

import br.com.michael_fausto.formulaSAE.model.car.brakes.BrakeSensorData;

import java.util.Random;

public class BrakeMock {

    private static final Random random = new Random();

    public static BrakeSensorData brakeMockFL() {
        Integer discBreakTemperature = 100 + random.nextInt(350);
        Integer brakeFluidPressure = 5 + random.nextInt(50);

        return new BrakeSensorData(
                "FRONT_LEFT",
                String.valueOf(discBreakTemperature),
                String.valueOf(brakeFluidPressure)
        );
    }

    public static BrakeSensorData brakeMockFR() {
        Integer discBreakTemperature = 100 + random.nextInt(350);
        Integer brakeFluidPressure = 5 + random.nextInt(50);

        return new BrakeSensorData(
                "FRONT_RIGHT",
                String.valueOf(discBreakTemperature),
                String.valueOf(brakeFluidPressure)
        );
    }

    public static BrakeSensorData brakeMockBR() {
        Integer discBreakTemperature = 100 + random.nextInt(350);
        Integer brakeFluidPressure = 5 + random.nextInt(50);

        return new BrakeSensorData(
                "FRONT_RIGHT",
                String.valueOf(discBreakTemperature),
                String.valueOf(brakeFluidPressure)
        );
    }

    public static BrakeSensorData brakeMockBL() {
        Integer discBreakTemperature = 100 + random.nextInt(350);
        Integer brakeFluidPressure = 5 + random.nextInt(50);

        return new BrakeSensorData(
                "FRONT_RIGHT",
                String.valueOf(discBreakTemperature),
                String.valueOf(brakeFluidPressure)
        );
    }



}
