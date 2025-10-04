package br.com.michael_fausto.formulaSAE.model.mocks;

import br.com.michael_fausto.formulaSAE.model.car.cooling.CoolingSensorData;

import java.util.Random;

public class CoolingMock {

    public static CoolingSensorData coolingMockSimulatadArduino() {
        Random random = new Random();
        Integer coolingSystemTemperature = 20 + random.nextInt(100);
        Double reservoirVolume = 0.5 + random.nextDouble(17);
        Boolean fan = random.nextBoolean();

        return new CoolingSensorData(
                String.valueOf(coolingSystemTemperature),
                String.valueOf(reservoirVolume),
                String.valueOf(fan)
        );
    }

}
