package br.com.michael_fausto.formulaSAE.mapper.car.cooling;

import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingEntity;
import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingSetupEntity;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeSetupDTO;
import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingSetupDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoolingSetupMapper {

    CoolingSetupEntity toEntity(CoolingSetupDTO dto);

    CoolingSetupDTO toDto(CoolingSetupEntity coolingSetupEntity);

    List<CoolingSetupDTO> listDto(List<CoolingSetupEntity> all);
}
