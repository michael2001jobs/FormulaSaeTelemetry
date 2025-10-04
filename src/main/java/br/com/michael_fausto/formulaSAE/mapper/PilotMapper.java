package br.com.michael_fausto.formulaSAE.mapper;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.model.Pilot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PilotMapper {

    @Mapping(target = "id", ignore = true)
    PilotEntity convertToEntity(Pilot pilot);
}
