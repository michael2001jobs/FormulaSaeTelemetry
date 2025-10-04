package br.com.michael_fausto.formulaSAE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class TelemetryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PilotEntity pilot;

    @OneToMany(mappedBy = "telemetry", cascade = CascadeType.ALL)
    private List<TelemetryDataEntity> dataEntries;

    private LocalDateTime timestamp;

    public TelemetryEntity(Long id, PilotEntity pilot, List<TelemetryDataEntity> dataEntries, LocalDateTime timestamp) {
        this.id = id;
        this.pilot = pilot;
        this.dataEntries = dataEntries;
        this.timestamp = timestamp;
    }

    public TelemetryEntity() {
        
    }
}
