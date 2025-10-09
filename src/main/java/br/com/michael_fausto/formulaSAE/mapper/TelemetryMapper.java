package br.com.michael_fausto.formulaSAE.mapper;

import br.com.michael_fausto.formulaSAE.entity.TelemetryEntity;
import br.com.michael_fausto.formulaSAE.mapper.car.brake.BrakeMapper;
import br.com.michael_fausto.formulaSAE.mapper.car.cooling.CoolingMapper;
import br.com.michael_fausto.formulaSAE.model.telemetry.TelemetryDTO;
import br.com.michael_fausto.formulaSAE.model.telemetry.TelemetrySummaryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {BrakeMapper.class,
                CoolingMapper.class})
public interface TelemetryMapper {

    TelemetryEntity toEntity(TelemetryDTO telemetryDTO);

    TelemetryDTO toDto(TelemetryEntity telemetry);

    TelemetrySummaryDTO toSummary(TelemetryEntity telemetry);

}
