package fr.diginamic.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private Long temperature;
    @NotBlank
    private Long pressure;
    @NotBlank
    private Integer humidity;
    @NotBlank
    private Long tempMin;
    @NotBlank
    private Long tempMax;
    @NotBlank
    private Long windSpeed;
    @NotBlank
    private Integer windDegrees;

}
