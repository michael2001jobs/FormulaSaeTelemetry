package br.com.michael_fausto.formulaSAE.repository.car.brake;

import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeSetupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrakeSetupRepository extends JpaRepository<BrakeSetupEntity, Long> {

    boolean existsBy();

}
