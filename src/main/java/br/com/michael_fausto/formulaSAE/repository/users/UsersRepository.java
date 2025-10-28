package br.com.michael_fausto.formulaSAE.repository.users;

import br.com.michael_fausto.formulaSAE.entity.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    UserDetails findByEmail(String email);
}
