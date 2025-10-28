package br.com.michael_fausto.formulaSAE.entity.telemetry;

import br.com.michael_fausto.formulaSAE.entity.car.CarEntity;
import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeEntity;
import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingEntity;
import br.com.michael_fausto.formulaSAE.entity.users.UsersEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UsersEntity user;

    @OneToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "telemetry_id")
    private List<BrakeEntity> brakeTelemetry;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "telemetry_id")
    private List<CoolingEntity> coolingTelemetry;

    public TelemetryEntity(UsersEntity user, CarEntity car) {
        this.user = user;
        this.car = car;
        this.timestamp = LocalDateTime.now();
        this.brakeTelemetry = new ArrayList<BrakeEntity>();
        this.coolingTelemetry = new ArrayList<CoolingEntity>();
    }
}
