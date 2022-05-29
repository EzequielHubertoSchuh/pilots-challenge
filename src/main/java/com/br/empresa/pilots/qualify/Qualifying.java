package com.br.empresa.pilots.qualify;

import com.br.empresa.pilots.constructor.Constructor;
import com.br.empresa.pilots.race.Race;
import com.br.empresa.pilots.driver.Driver;
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
@ApiModel(value  = "Qualifying", description = "This is a sample entity from the Api package.")
@Table(name = "tb_qualifying")
@SequenceGenerator(name = "seq_qualifying", sequenceName = "seq_qualifying",allocationSize = 1)
public class Qualifying extends RepresentationModel<Qualifying> {

    @ApiModelProperty(value = "Identifier")
    @Id @GeneratedValue(generator = "seq_qualifying",strategy = GenerationType.SEQUENCE)
    @Column(name = "qualifyingid")
    private Long id;

    @ApiModelProperty(value = "Race", required = true)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raceid")
    private Race race;

    @ApiModelProperty(value = "Driver", required = true)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driverid")
    private Driver driver;

    @ApiModelProperty(value = "Constructor", required = true)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constructorid")
    private Constructor constructor;

    @NotNull
    @Column(name = "number")
    @ApiModelProperty(value = "Number", required = true)
    private Integer number;

    @NotNull
    @Column(name = "position")
    @ApiModelProperty(value = "Position", required = true)
    private Integer position;

    @ApiModelProperty(value = "q1")
    @Column(name = "q1")
    private LocalTime q1;

    @ApiModelProperty(value = "q2")
    @Column(name = "q2")
    private LocalTime q2;

    @ApiModelProperty(value = "q3")
    @Column(name = "q3")
    private LocalTime q3;

}
