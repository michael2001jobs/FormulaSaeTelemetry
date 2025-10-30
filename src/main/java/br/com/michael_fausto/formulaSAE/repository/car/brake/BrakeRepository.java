package br.com.michael_fausto.formulaSAE.repository.car.brake;

import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrakeRepository extends JpaRepository<BrakeEntity, Long> {

}
