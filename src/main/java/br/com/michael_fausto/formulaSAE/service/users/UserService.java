package br.com.michael_fausto.formulaSAE.service.users;

import br.com.michael_fausto.formulaSAE.entity.users.UsersEntity;
import br.com.michael_fausto.formulaSAE.mapper.users.UsersMapper;
import br.com.michael_fausto.formulaSAE.model.users.UsersDTO;
import br.com.michael_fausto.formulaSAE.model.auth.UsersRegisterDTO;
import br.com.michael_fausto.formulaSAE.repository.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    private final UsersRepository repository;
    private final UsersMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UsersDTO convertDto(UsersEntity entity) {
        UsersDTO dto = mapper.toDto(entity);
        logger.info("Pilot convert in DTO: {}", dto);
        return dto;
    }

    @Transactional
    public UsersDTO findByEmail(String email) {

        UsersEntity entity = repository.findByEmail(email);
        UsersDTO dto = convertDto(entity);

        logger.debug("Pilot find by id : {}", entity);
        return dto;
    }

    @Transactional
    public UsersDTO updatePilot(UsersRegisterDTO dto, String email) {
        UsersEntity entity = repository.findByEmail(email);

        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());

        repository.save(entity);
        logger.info("Pilot update: {}", entity);

        return mapper.toDto(entity);
    }

    @Transactional
    public UsersDTO deletePilot(String email) {
        try {
            repository.deleteByEmail(email);
            logger.info("Pilot as been deleted");
            return findByEmail(email);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Pilot with" + email + " not found");
        }
    }
}
    
