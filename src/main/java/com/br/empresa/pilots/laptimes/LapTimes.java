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
@SequenceGenerator(name = "seq_laptimes", sequenceName = "seq_laptimes",allocationSize = 1)
public class LapTimes extends RepresentationModel<LapTimes>{

    @Id
    @GeneratedValue(generator = "seq_laptimes",strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(value = "Identifed")
    @Column(name = "laptimesid")
    private Long id;

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

    @ApiModelProperty(value = "Lap", required = true)
    @NotNull(message="Lap cannot be null")
    @Column(name = "lap")
    private Integer lap;

    @ApiModelProperty(value = "Position", required = true)
    @NotNull(message="Position cannot be null")
    @Column(name = "position")
    private Integer position;

    @ApiModelProperty(value = "Time", required = true)
    @Column(name = "time")
    private LocalTime time;

    @ApiModelProperty(value = "Milliseconds", required = true)
    @Column(name = "milliseconds")
    private Integer milliseconds;
}
