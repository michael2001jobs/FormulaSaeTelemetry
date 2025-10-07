package br.com.michael_fausto.formulaSAE.repository;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PilotRepository extends JpaRepository<PilotEntity, Long> {

    void deleteByEmail(String email);

    Long id(Long id);
}
