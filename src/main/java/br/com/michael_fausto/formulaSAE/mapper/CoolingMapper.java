package br.com.michael_fausto.formulaSAE.mapper;

import br.com.michael_fausto.formulaSAE.entity.car.BrakeEntity;
import br.com.michael_fausto.formulaSAE.entity.car.CoolingEntity;
import br.com.michael_fausto.formulaSAE.model.dto.CoolingDTO;
import br.com.michael_fausto.formulaSAE.model.dto.BrakeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoolingMapper {

    CoolingEntity toEntity(CoolingDTO brakeDTO);

    CoolingDTO toDto(CoolingEntity brakeEntity);
}
