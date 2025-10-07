package br.com.michael_fausto.formulaSAE.repository.car;

import br.com.michael_fausto.formulaSAE.entity.car.BrakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrakeTelemetryRepository extends JpaRepository<BrakeEntity, Long> {

    BrakeEntity findTopByOrderByIdDesc();
}
