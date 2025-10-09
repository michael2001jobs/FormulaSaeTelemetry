package br.com.michael_fausto.formulaSAE.mapper.car.brake;

import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeSetupEntity;
import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeEntity;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeSetupDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrakeSetupMapper {

    BrakeSetupEntity toEntity(BrakeSetupDTO brakeSetupDTO);

    BrakeSetupDTO toDto(BrakeSetupEntity brakeSetupEntity);
}
