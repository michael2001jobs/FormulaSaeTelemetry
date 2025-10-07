package br.com.michael_fausto.formulaSAE.mapper;

import br.com.michael_fausto.formulaSAE.entity.car.BrakeEntity;
import br.com.michael_fausto.formulaSAE.model.dto.BrakeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrakeMapper {

    BrakeEntity toEntity(BrakeDTO brakeDTO);

    BrakeDTO toDto(BrakeEntity brakeEntity);
}
