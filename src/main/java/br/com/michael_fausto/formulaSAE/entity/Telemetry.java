package br.com.michael_fausto.formulaSAE.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Telemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private PilotEntity pilot;

    @OneToMany(mappedBy = "telemetry", cascade = CascadeType.ALL)
    private List<TelemetryData> data;

}
