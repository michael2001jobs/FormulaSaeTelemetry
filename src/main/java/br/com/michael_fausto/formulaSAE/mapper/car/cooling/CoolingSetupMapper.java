package br.com.michael_fausto.formulaSAE.mapper.car.cooling;

import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingEntity;
import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingSetupEntity;
import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingSetupDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoolingSetupMapper {

    @Mapping(target = "id", ignore = true)
    CoolingSetupEntity toEntity(CoolingSetupDTO dto);

    CoolingSetupDTO toDto(CoolingSetupEntity entity);
}
