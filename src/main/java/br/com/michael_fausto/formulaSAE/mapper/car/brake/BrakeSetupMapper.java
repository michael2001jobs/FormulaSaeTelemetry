package br.com.michael_fausto.formulaSAE.mapper.car.brake;

import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeSetupEntity;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeSetupDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrakeSetupMapper {

    BrakeSetupEntity toEntity(BrakeSetupDTO brakeSetupDTO);

    BrakeSetupDTO toDto(BrakeSetupEntity brakeSetupEntity);

    List<BrakeSetupDTO> listDto(List<BrakeSetupEntity> all);
}
