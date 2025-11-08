package br.com.michael_fausto.formulaSAE.entity.car;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String versionOrModel;

    @Column(name = "create_in", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createIn;

    @OneToOne(fetch = FetchType.EAGER)
    private SetupCarEntity subSystemSetup;

    public CarEntity(String name, String versionOrModel, SetupCarEntity subSystemSetup) {
        this.name = name;
        this.versionOrModel = versionOrModel;
        this.createIn = LocalDateTime.now();
        this.subSystemSetup = subSystemSetup;
    }
}


