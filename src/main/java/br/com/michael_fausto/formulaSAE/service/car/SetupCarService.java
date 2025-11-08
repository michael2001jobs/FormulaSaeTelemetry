package br.com.michael_fausto.formulaSAE.service.car;

import br.com.michael_fausto.formulaSAE.entity.car.SetupCarEntity;
import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeSetupEntity;
import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingSetupEntity;
import br.com.michael_fausto.formulaSAE.mapper.car.SetupCarMapper;
import br.com.michael_fausto.formulaSAE.model.car.SetupCarDTO;
import br.com.michael_fausto.formulaSAE.repository.car.SetupCarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SetupCarService {

    private final SetupCarRepository repository;
    private final SetupCarMapper mapper;

    public SetupCarDTO create(SetupCarDTO dto) {
        SetupCarEntity entity = new SetupCarEntity(dto.name());
        repository.save(entity);

        return dto;
    }

    public void setBrakeSetup(SetupCarEntity entity, BrakeSetupEntity brakeSetup) {
        entity.setBrakeSetup(brakeSetup);
        repository.save(entity);
    }

    public void setCoolingSetup(SetupCarEntity entity, CoolingSetupEntity coolingSetup) {
        entity.setCoolingSetup(coolingSetup);
        repository.save(entity);
    }

    public SetupCarDTO findByName(String name) {
        return mapper.toDto(repository.findByName(name));
    }

}
