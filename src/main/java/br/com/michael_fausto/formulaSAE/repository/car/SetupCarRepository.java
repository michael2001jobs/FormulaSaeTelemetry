package br.com.michael_fausto.formulaSAE.repository.car;

import br.com.michael_fausto.formulaSAE.entity.car.SetupCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetupCarRepository extends JpaRepository<SetupCarEntity, Long> {
    SetupCarEntity findByName(String name);
}
