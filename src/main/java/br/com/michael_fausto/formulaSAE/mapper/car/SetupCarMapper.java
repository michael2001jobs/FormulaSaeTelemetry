package br.com.michael_fausto.formulaSAE.mapper.car;

import br.com.michael_fausto.formulaSAE.entity.car.SetupCarEntity;
import br.com.michael_fausto.formulaSAE.model.car.SetupCarDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SetupCarMapper {

    SetupCarEntity toEntity(SetupCarDTO dto);

    SetupCarDTO toDto(SetupCarEntity entity);

    List<SetupCarDTO> listDto(List<SetupCarEntity> all);
}
