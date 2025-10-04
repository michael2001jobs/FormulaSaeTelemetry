package br.com.michael_fausto.formulaSAE.repository;

import br.com.michael_fausto.formulaSAE.entity.TelemetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryEntity, Long> {
    List<TelemetryEntity> findByPilotId(Long pilotId);
}
