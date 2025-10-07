package br.com.michael_fausto.formulaSAE.mapper;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.model.dto.PilotDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PilotMapper {

    PilotEntity toEntity(PilotDTO pilotDTO);


    PilotDTO toDto(PilotEntity pilot);

}
