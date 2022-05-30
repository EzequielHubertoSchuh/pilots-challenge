package com.br.empresa.pilots.season;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ApiModel(value = "Season", description = "This is a sample entity from the Api package.")
@Table(name = "tb_season")
@SequenceGenerator(name = "seq_season", sequenceName = "seq_status", allocationSize = 1)
public class Season extends RepresentationModel<Season> {

    @Id
    @GeneratedValue(generator = "seq_season", strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "seasonid")
    private Long id;

    @NotNull(message="Year cannot be null")
    @Column(name = "year")
    @ApiModelProperty(value = "Year", required = true)
    private Integer year;

    @URL
    @NotNull(message="U.R.L cannot be null")
    @Column(name = "url")
    @ApiModelProperty(value = "URL", required = true)
    private String url;
}
