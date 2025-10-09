package br.com.michael_fausto.formulaSAE.repository.car.cooling;

import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingSetupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoolingSetupRepository extends JpaRepository<CoolingSetupEntity, Long> {

    boolean existsBy();
}
