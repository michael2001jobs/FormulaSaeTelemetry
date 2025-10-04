package br.com.michael_fausto.formulaSAE.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "pilot")
public class PilotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "pilot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelemetryEntity> telemetryList;

    public PilotEntity(Long id, String name, String email, List<TelemetryEntity> telemetryList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telemetryList = telemetryList;
    }

    public PilotEntity() {
    }

    public PilotEntity(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TelemetryEntity> getTelemetryList() {
        return telemetryList;
    }

    public void setTelemetryList(List<TelemetryEntity> telemetryList) {
        this.telemetryList = telemetryList;
    }
}
