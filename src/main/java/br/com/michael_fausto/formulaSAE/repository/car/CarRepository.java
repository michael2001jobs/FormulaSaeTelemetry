package br.com.michael_fausto.formulaSAE.repository.car;

import br.com.michael_fausto.formulaSAE.entity.car.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
