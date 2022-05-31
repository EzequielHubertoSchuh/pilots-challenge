package com.br.empresa.pilots.constructorresult;

import com.br.empresa.pilots.driver.Driver;
import com.br.empresa.pilots.race.Race;
import com.br.empresa.pilots.status.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ApiModel(value  = "Constructor", description = "This is a sample entity from the Api package.")
@Table(name = "tb_constructor")
@SequenceGenerator(name = "seq_constructor", sequenceName = "seq_constructor", allocationSize = 1)
public class ConstructorResult extends RepresentationModel<ConstructorResult> {

    @ApiModelProperty(value = "Identifier")
    @Id  @GeneratedValue(generator = "seq_constructor", strategy = GenerationType.SEQUENCE)
    @Column(name = "constructorid")
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

    @ApiModelProperty(value = "Points", required = true)
    @NotNull(message="Points cannot be null")
    @Column(name = "points")
    private Integer points;

    @ApiModelProperty(value = "Status", required = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statusid")
    private Status status;

}
