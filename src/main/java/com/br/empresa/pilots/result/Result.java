package com.br.empresa.pilots.result;

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
@ApiModel(value  = "Result", description = "This is a sample entity from the Api package.")
@Table(name = "tb_result")
@SequenceGenerator(name = "seq_result", sequenceName = "seq_result",allocationSize = 1)
public class Result extends RepresentationModel<Result>{

    @Id
    @GeneratedValue(generator = "seq_result",strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(value = "Identifed")
    @Column(name = "resultid")
    private Long id;

    @ApiModelProperty(value = "Race", required = true)
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "raceid")
    private Race race;

    @ApiModelProperty(value = "Driver", required = true)
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driverid")
    private Driver driver;

    @ApiModelProperty(value = "Constructor", required = true)
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "constructorid")
    private Constructor constructor;

    @ApiModelProperty(value = "Number")
    @Column(name = "number")
    private Integer number;

    @ApiModelProperty(value = "Grid", required = true)
    @NotNull
    @Column(name = "grid")
    private Integer grid;

    @ApiModelProperty(value = "Position")
    @Column(name = "position")
    private Integer position;

    @ApiModelProperty(value = "Position Text", required = true)
    @NotNull
    @Column(name = "positiontext")
    private String positionText;

    @ApiModelProperty(value = "Position Order", required = true)
    @NotNull
    @Column(name = "positionorder")
    private Number positionOrder;

    @ApiModelProperty(value = "Points", required = true)
    @NotNull
    @Column(name = "points")
    private Integer points;

    @ApiModelProperty(value = "Laps", required = true)
    @NotNull
    @Column(name = "laps")
    private Integer laps;

    @ApiModelProperty(value = "Time")
    @Column(name = "time")
    private String time;

    @ApiModelProperty(value = "Milliseconds")
    @Column(name = "milliseconds")
    private Integer milliseconds;

    @ApiModelProperty(value = "fastest Lap")
    @Column(name = "fastestlap")
    private Integer fastestlap;

    @ApiModelProperty(value = "Rank")
    @Column(name = "rank")
    private Integer rank;

    @ApiModelProperty(value = "Fastest lap time")
    @Column(name = "fastestlaptime")
    private LocalTime fastestlaptime;

    @ApiModelProperty(value = "Fastest lap speed")
    @Column(name = "fastestlapspeed")
    private Integer fastestlapspeed;

    @ApiModelProperty(value = "Status id", required = true)
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusid")
    private Status status;

}
