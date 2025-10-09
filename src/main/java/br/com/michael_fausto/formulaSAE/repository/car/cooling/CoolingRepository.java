package br.com.michael_fausto.formulaSAE.repository.car.cooling;

import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoolingRepository extends JpaRepository<CoolingEntity, Long> {

    CoolingEntity findTopByOrderByIdDesc();

}
