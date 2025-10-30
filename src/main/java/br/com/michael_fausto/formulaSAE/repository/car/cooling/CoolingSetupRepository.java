package br.com.michael_fausto.formulaSAE.repository.car.cooling;

import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingSetupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface CoolingSetupRepository extends JpaRepository<CoolingSetupEntity, Long> {

    boolean existsBy();

    void deleteByName(String profileName);

    CoolingSetupEntity findByName(String name);
}
