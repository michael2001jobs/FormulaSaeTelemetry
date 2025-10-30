package br.com.michael_fausto.formulaSAE.mapper.car;

import br.com.michael_fausto.formulaSAE.entity.car.CarEntity;
import br.com.michael_fausto.formulaSAE.model.car.CarDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarEntity toEntity(CarDTO dto);

    CarDTO toDto(CarEntity entity);

    List<CarDTO> listDto(List<CarEntity> all);
}
