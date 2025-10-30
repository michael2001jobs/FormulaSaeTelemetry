package br.com.michael_fausto.formulaSAE.entity.telemetry;

import br.com.michael_fausto.formulaSAE.entity.car.CarEntity;
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
@Table(name = "telemetry")
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 100)
    String name;

    @ManyToOne
    private UsersEntity user;

    @OneToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public TelemetryEntity(UsersEntity user, CarEntity car, String name) {
        this.user = user;
        this.name = name;
        this.car = car;
        this.timestamp = LocalDateTime.now();
    }
}
