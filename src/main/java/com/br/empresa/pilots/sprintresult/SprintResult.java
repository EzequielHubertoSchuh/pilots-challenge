package com.br.empresa.pilots.sprintresult;

import com.br.empresa.pilots.constructor.Constructor;
import com.br.empresa.pilots.driver.Driver;
import com.br.empresa.pilots.race.Race;
import com.br.empresa.pilots.result.Result;
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
@ApiModel(value  = "Sprint Result", description = "This is a sample entity from the Api package.")
@Table(name = "tb_sprintresult")
@SequenceGenerator(name = "seq_sprintresult", sequenceName = "seq_sprintresult",allocationSize = 1)
public class SprintResult extends RepresentationModel<SprintResult> {

    @ApiModelProperty(value = "Identifier")
    @Id @GeneratedValue(generator = "seq_sprintresult",strategy = GenerationType.SEQUENCE)
    @Column(name = "sprintresultid")
    private Long id;

    @ApiModelProperty(value = "Result", required = true)
    @NotNull(message="Result cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resultid")
    private Result result;

    @ApiModelProperty(value = "Race", required = true)
    @NotNull(message="Race cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raceid")
    private Race race;

    @ApiModelProperty(value = "Driver", required = true)
    @NotNull(message="Driver cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driverid")
    private Driver driver;

    @ApiModelProperty(value = "Constructor", required = true)
    @NotNull(message="Constructor cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constructorid")
    private Constructor constructor;

    @NotNull(message="Number cannot be null")
    @Column(name = "number")
    @ApiModelProperty(value = "Number", required = true)
    private Integer number;

    @NotNull(message="Grid cannot be null")
    @Column(name = "grid")
    @ApiModelProperty(value = "Grid", required = true)
    private Integer grid;

    @Column(name = "position")
    @ApiModelProperty(value = "Position")
    private Integer position;

    @NotNull(message="position Text cannot be null")
    @Column(name = "positiontext")
    @ApiModelProperty(value = "Position text", required = true)
    private String positionText;

    @Column(name = "positionorder")
    @ApiModelProperty(value = "Position Order", required = true)
    private Integer positionOrder;

    @Column(name = "points")
    @ApiModelProperty(value = "Points", required = true)
    @NotNull(message="Points cannot be null")
    private Integer points;

    @Column(name = "laps")
    @ApiModelProperty(value = "Laps", required = true)
    @NotNull(message="Laps cannot be null")
    private Integer laps;

    @ApiModelProperty(value = "Time", required = true)
    @Column(name = "time")
    private String time;

    @ApiModelProperty(value = "Milliseconds", required = true)
    @Column(name = "milliseconds")
    private Integer milliseconds;

    @ApiModelProperty(value = "Fastest Lap", required = true)
    @Column(name = "fastestlap")
    private Integer fastestLap;

    @ApiModelProperty(value = "Fastest Lap", required = true)
    @Column(name = "fastestlaptime")
    private LocalTime fastestLapTime;

    @ApiModelProperty(value = "Status id", required = true)
    @NotNull(message="Status cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusid")
    private Status status;

}
