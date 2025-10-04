package br.com.michael_fausto.formulaSAE.entity;

import br.com.michael_fausto.formulaSAE.entity.car.BrakeTelemetryEntity;
import br.com.michael_fausto.formulaSAE.entity.car.CoolingTelemetryEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = BrakeTelemetryEntity.class, name = "brake"),
    @JsonSubTypes.Type(value = CoolingTelemetryEntity.class, name = "cooling")
})
public abstract class TelemetryDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TelemetryEntity telemetry;

    public TelemetryDataEntity(Long id, TelemetryEntity telemetry) {
        this.id = id;
        this.telemetry = telemetry;
    }

    public TelemetryDataEntity() {
    }
}
