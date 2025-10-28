package br.com.michael_fausto.formulaSAE.service.auth;

import br.com.michael_fausto.formulaSAE.mapper.users.UsersMapper;
import br.com.michael_fausto.formulaSAE.repository.users.UsersRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
}

