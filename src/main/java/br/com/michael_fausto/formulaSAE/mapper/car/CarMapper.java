package br.com.michael_fausto.formulaSAE.mapper.car;

import br.com.michael_fausto.formulaSAE.entity.car.CarEntity;
import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeEntity;
import br.com.michael_fausto.formulaSAE.model.car.CarDTO;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarEntity toEntity(CarDTO dto);

    CarDTO toDto(CarEntity entity);
}
