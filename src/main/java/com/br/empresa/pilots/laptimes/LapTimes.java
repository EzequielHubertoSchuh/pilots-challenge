package com.br.empresa.pilots.laptimes;

import com.br.empresa.pilots.circuit.Circuit;
import com.br.empresa.pilots.driver.Driver;
import com.br.empresa.pilots.race.Race;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ApiModel(value  = "Lap Times", description = "This is a sample entity from the Api package.")
@Table(name = "tb_laptimes")
public class LapTimes extends RepresentationModel<LapTimes> implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identifed")
    @Column(name = "laptimesid")
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

    @ApiModelProperty(value = "Lap", required = true)
    @NotNull
    @Column(name = "lap")
    private Integer lap;

    @ApiModelProperty(value = "Position", required = true)
    @NotNull
    @Column(name = "position")
    private Integer position;

    @ApiModelProperty(value = "Time", required = true)
    @Column(name = "time")
    private LocalTime time;

    @ApiModelProperty(value = "Milliseconds", required = true)
    @Column(name = "milliseconds")
    private Integer milliseconds;
}
