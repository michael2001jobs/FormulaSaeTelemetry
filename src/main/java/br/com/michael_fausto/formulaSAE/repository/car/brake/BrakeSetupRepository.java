package br.com.michael_fausto.formulaSAE.repository.car.brake;

import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeSetupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BrakeSetupRepository extends JpaRepository<BrakeSetupEntity, Long> {

    boolean existsBy();

    BrakeSetupEntity findByName(String name);

    void deleteByName(String name);
}
