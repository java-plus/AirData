package fr.diginamic.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesureMeteo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private ZonedDateTime date;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "StationDeMesure_id")
    private StationDeMesureMeteo stationDeMesure;

    @NotBlank
    private String weatherDescription;
    @NotBlank
    private String weatherIcon;
    @NotNull
    private Long temperature;
    @NotNull
    private Long pressure;
    @NotNull
    private Integer humidity;
    @NotNull
    private Long tempMin;
    @NotNull
    private Long tempMax;
    @NotNull
    private Long windSpeed;
    @NotNull
    private Integer windDegrees;

}
