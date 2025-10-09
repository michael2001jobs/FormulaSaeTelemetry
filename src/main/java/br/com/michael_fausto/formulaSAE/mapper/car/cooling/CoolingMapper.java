package br.com.michael_fausto.formulaSAE.mapper.car.cooling;

import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingEntity;
import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoolingMapper {

    CoolingEntity toEntity(CoolingDTO brakeDTO);

    CoolingDTO toDto(CoolingEntity brakeEntity);
}
