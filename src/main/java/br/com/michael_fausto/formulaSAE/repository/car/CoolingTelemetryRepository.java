package br.com.michael_fausto.formulaSAE.repository.car;

import br.com.michael_fausto.formulaSAE.entity.car.CoolingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoolingTelemetryRepository extends JpaRepository<CoolingEntity, Long> {

    CoolingEntity findTopByOrderByIdDesc();

}
