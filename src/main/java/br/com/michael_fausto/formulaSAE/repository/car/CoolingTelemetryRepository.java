package br.com.michael_fausto.formulaSAE.repository.car;

import br.com.michael_fausto.formulaSAE.entity.car.CoolingTelemetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoolingTelemetryRepository extends JpaRepository<CoolingTelemetryEntity, Long> {

    CoolingTelemetryEntity findTopByOrderByIdDesc();
}
