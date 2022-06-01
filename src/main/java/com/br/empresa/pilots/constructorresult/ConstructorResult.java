package com.br.empresa.pilots.constructorresult;

import com.br.empresa.pilots.constructor.Constructor;
import com.br.empresa.pilots.race.Race;
import com.br.empresa.pilots.status.Status;
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
@ApiModel(value  = "Constructor Result", description = "This is a sample entity from the Api package.")
@Table(name = "tb_constructorresult")
@SequenceGenerator(name = "seq_constructorresult", sequenceName = "seq_constructorresult", allocationSize = 1)
public class ConstructorResult extends RepresentationModel<ConstructorResult> {

    @ApiModelProperty(value = "Identifier")
    @Id  @GeneratedValue(generator = "seq_constructorresult", strategy = GenerationType.SEQUENCE)
    @Column(name = "constructorresultid")
    private Long id;

    @ApiModelProperty(value = "Race", required = true)
    @NotNull(message="Race cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raceid")
    private Race race;

    @ApiModelProperty(value = "Constructorid", required = true)
    @NotNull(message="Constructor cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constructorid")
    private Constructor constructor;

    @ApiModelProperty(value = "Points", required = true)
    @NotNull(message="Points cannot be null")
    @Column(name = "points")
    private Integer points;

    @ApiModelProperty(value = "Status", required = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statusid")
    private Status status;

}
