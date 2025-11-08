package br.com.michael_fausto.formulaSAE.service.car.cooling;

import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingSetupEntity;
import br.com.michael_fausto.formulaSAE.mapper.car.cooling.CoolingSetupMapper;
import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingSetupDTO;
import br.com.michael_fausto.formulaSAE.repository.car.cooling.CoolingSetupRepository;
import br.com.michael_fausto.formulaSAE.service.interfaces.SetupCarServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoolingSetupService implements SetupCarServiceInterface<CoolingSetupDTO> {

    private final CoolingSetupMapper mapper;
    private final CoolingSetupRepository repository;
    private final Logger logger = LoggerFactory.getLogger(CoolingSetupService.class);


    @Override
    public CoolingSetupDTO create(CoolingSetupDTO dto) {
        CoolingSetupEntity entity = new CoolingSetupEntity(dto);

        repository.save(entity);

        logger.debug("CoolingSetup convert in entity : {}", entity);
        return mapper.toDto(entity);
    }

    @Override
    public CoolingSetupDTO deleteById(Long id) {
        try {
            CoolingSetupEntity entity = mapper.toEntity(findById(id));
            CoolingSetupDTO responseDTO = mapper.toDto(entity);

            repository.deleteById(id);
            logger.info("Cooling Setup as been deleted --- method id");

            return responseDTO;
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Brake Setup not found");
        }
    }

    @Override
    public CoolingSetupDTO updateName(String updateName, Long id) {
        CoolingSetupEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cooling Setup not found"));

        entity.setName(updateName);

        repository.save(entity);
        logger.info("CoolingSetup update: {}", entity);

        return mapper.toDto(entity);
    }

    @Override
    public CoolingSetupDTO copyAndEdit(Long id) {
        CoolingSetupDTO copy = create(findById(id));
        logger.debug("Cooling setup copied");

        return copy;
    }


    @Override
    public Boolean isEmpty() {
        return repository.existByIdNoNull();
    }

    @Override
    public List<CoolingSetupDTO> showAll() {
        if (isEmpty()) {
            return mapper.listDto(repository.findAll());
        } else {
            throw new EntityNotFoundException("Cooling Setup not found");
        }
    }

    @Override
    public CoolingSetupDTO findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cooling Setup not found")));
    }

}
