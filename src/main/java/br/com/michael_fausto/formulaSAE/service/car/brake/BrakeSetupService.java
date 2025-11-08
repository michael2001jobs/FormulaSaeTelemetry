package br.com.michael_fausto.formulaSAE.service.car.brake;

import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeSetupEntity;
import br.com.michael_fausto.formulaSAE.mapper.car.brake.BrakeSetupMapper;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeSetupDTO;
import br.com.michael_fausto.formulaSAE.repository.car.brake.BrakeSetupRepository;
import br.com.michael_fausto.formulaSAE.service.interfaces.SetupCarServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class BrakeSetupService implements SetupCarServiceInterface<BrakeSetupDTO> {

    private final BrakeSetupRepository repository;
    private final BrakeSetupMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(BrakeSetupService.class);


    @Override
    public BrakeSetupDTO create(BrakeSetupDTO dto) {
        BrakeSetupEntity entity = new BrakeSetupEntity(dto);

        repository.save(entity);

        logger.debug("BrakeSetup convert in build setup: {}", entity);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public BrakeSetupDTO deleteById(Long id) {
        try {
            BrakeSetupEntity entity = mapper.toEntity(findById(id));
            BrakeSetupDTO responseDTO = mapper.toDto(entity);

            repository.delete(entity);
            logger.info("Brake Setup as been deleted --- method id" );

            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Brake Setup not found");
        }
    }

    @Override
    @Transactional
    public BrakeSetupDTO updateName(String updateName, Long id) {
        BrakeSetupEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brake Setup not found"));

        entity.setName(updateName);

        repository.save(entity);
        logger.info("BrakeSetup update: {}", entity);

        return mapper.toDto(entity);
    }

    @Override
    public BrakeSetupDTO copyAndEdit(Long id) {
        BrakeSetupDTO copy = create(findById(id));
        logger.debug("Brake setup copied");

        return copy;
    }

    @Override
    public Boolean isEmpty() {
        return repository.existsBy();
    }

    @Override
    public List<BrakeSetupDTO> showAll() {
        if (!isEmpty()){
            return mapper.listDto(repository.findAll());
        }
        else {
            throw new EntityNotFoundException("Brake setup not found");
        }
    }

    @Override
    public BrakeSetupDTO findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Brake Setup not found")));
    }
}
