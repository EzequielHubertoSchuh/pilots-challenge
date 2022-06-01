package com.br.empresa.pilots.driverstanding;


import com.br.empresa.pilots.driver.Driver;
import com.br.empresa.pilots.race.Race;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ApiModel(value  = "Driver Standings", description = "This is a sample entity from the Api package.")
@Table(name = "tb_driverstanding")
@SequenceGenerator(name = "driverstanding", sequenceName = "driverstanding", allocationSize = 1)
public class DriverStanding extends RepresentationModel<DriverStanding> {

    @Id @GeneratedValue(generator = "seq_driver",strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "driverstandingid")
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

    @NotNull(message="Points cannot be null")
    @Column(name = "points")
    @ApiModelProperty(value = "Points", required = true)
    private Integer points;

    @Column(name = "position")
    @ApiModelProperty(value = "Position")
    @NotNull(message="Position cannot be null")
    private Integer position;

    @Column(name = "positiontext")
    @ApiModelProperty(value = "Position text")
    @NotNull(message="Position Text cannot be null")
    private String positionText;

    @Column(name = "wins")
    @ApiModelProperty(value = "Wins")
    @NotNull(message="Wins cannot be null")
    private Integer wins;

}
