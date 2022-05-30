package com.br.empresa.pilots.pitstop;

import com.br.empresa.pilots.constructor.Constructor;
import com.br.empresa.pilots.driver.Driver;
import com.br.empresa.pilots.race.Race;
import com.br.empresa.pilots.status.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ApiModel(value  = "Pit Stops", description = "This is a sample entity from the Api package.")
@Table(name = "tb_pitstop")
@SequenceGenerator(name = "seq_pitstop", sequenceName = "seq_pitstop",allocationSize = 1)
public class PitStop extends RepresentationModel<PitStop>{

    @Id
    @GeneratedValue(generator = "seq_pitstop",strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(value = "Identifed")
    @Column(name = "pitstopid")
    private Long id;

    @ApiModelProperty(value = "Race", required = true)
    @NotNull(message="Race cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "raceid")
    private Race race;

    @ApiModelProperty(value = "Driver", required = true)
    @NotNull(message="Driver cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driverid")
    private Driver driver;

    @ApiModelProperty(value = "Stop")
    @NotNull(message="Stop cannot be null")
    @Column(name = "stop")
    private Integer stop;

    @ApiModelProperty(value = "Grid", required = true)
    @NotNull(message="Grid cannot be null")
    @Column(name = "grid")
    private Integer grid;

    @ApiModelProperty(value = "Lap")
    @NotNull(message="Lap cannot be null")
    @Column(name = "lap")
    private Integer lap;

    @ApiModelProperty(value = "Time", required = true)
    @Column(name = "time")
    @NotNull(message="Time cannot be null")
    private LocalTime time;

    @ApiModelProperty(value = "Duration")
    @NotNull(message="Duration cannot be null")
    @Column(name = "duration")
    private String duration;

    @ApiModelProperty(value = "Milliseconds")
    @NotNull(message="Milliseconds cannot be null")
    @Column(name = "milliseconds")
    private Integer milliseconds;

}
