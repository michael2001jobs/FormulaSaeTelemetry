package br.com.michael_fausto.formulaSAE.service.auth;

import br.com.michael_fausto.formulaSAE.entity.users.UsersEntity;
import br.com.michael_fausto.formulaSAE.mapper.users.UsersMapper;
import br.com.michael_fausto.formulaSAE.model.auth.UsersRegisterDTO;
import br.com.michael_fausto.formulaSAE.repository.users.UsersRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final UsersRepository repository;
    private final UsersMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public void newUser(UsersRegisterDTO register) {
        if(repository.findByEmail(register.getEmail()) != null)
            throw new EntityExistsException("This is user has been register");

        String cripPassword = new BCryptPasswordEncoder().encode(register.getPassword());
        UsersEntity newUser = new UsersEntity(
                register.getName(),
                register.getEmail(),
                cripPassword);

        this.repository.save(newUser);
    }
}

