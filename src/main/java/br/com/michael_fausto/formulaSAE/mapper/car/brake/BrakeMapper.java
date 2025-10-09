package br.com.michael_fausto.formulaSAE.mapper.car.brake;

import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeEntity;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrakeMapper {

    BrakeEntity toEntity(BrakeDTO brakeDTO);

    BrakeDTO toDto(BrakeEntity brakeEntity);
}
