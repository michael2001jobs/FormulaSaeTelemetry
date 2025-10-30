package br.com.michael_fausto.formulaSAE.repository.users;

import br.com.michael_fausto.formulaSAE.entity.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    UsersEntity findByEmail(String email);

    void deleteByEmail(String email);
}
