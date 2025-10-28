package br.com.michael_fausto.formulaSAE.service.users;

import br.com.michael_fausto.formulaSAE.entity.users.UsersEntity;
import br.com.michael_fausto.formulaSAE.mapper.users.UsersMapper;
import br.com.michael_fausto.formulaSAE.model.users.UsersDTO;
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

    public void savePilot(UsersEntity entity) {
        repository.save(entity);
        logger.info("Pilot saved: {}", entity);
    }

    public UsersEntity convertEntity(UsersDTO dto) {
        UsersEntity entity = mapper.toEntity(dto);
        logger.info("Pilot convert in Entity : {}", entity);
        return entity;
    }

    @Transactional
    public UsersEntity findById(Long id) {
        UsersEntity entity = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Pilot Setup with " + id + " not found"));
        logger.debug("Pilot find by id : {}", entity);
        return entity;
    }

    @Transactional
    public UsersDTO updatePilot(UsersDTO dto, Long id) {
        UsersEntity entity = findById(id);

        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());

        repository.save(entity);
        logger.info("Pilot update: {}", entity);

        return mapper.toDto(entity);
    }

    @Transactional
    public void deletePilot(Long id) {
        try {
            repository.deleteById(id);
            logger.info("Pilot as been deleted");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Pilot with" + id + " not found");
        }
    }
}
    
