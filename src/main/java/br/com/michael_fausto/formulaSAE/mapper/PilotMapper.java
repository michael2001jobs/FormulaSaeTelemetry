package br.com.michael_fausto.formulaSAE.mapper;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.model.pilot.PilotDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PilotMapper {

    PilotEntity toEntity(PilotDTO pilotDTO);


    PilotDTO toDto(PilotEntity pilot);

}
