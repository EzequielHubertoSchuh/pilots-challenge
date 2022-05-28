package com.br.empresa.pilots.season;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Entity
@ApiModel(value = "Season", description = "This is a sample entity from the Api package.")
@Table(name = "tb_season")
@NoArgsConstructor
public class Season extends RepresentationModel<Season> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "seasonid")
    private Long id;

    @NotNull
    @Column(name = "year")
    @ApiModelProperty(value = "Year", required = true)
    private Integer year;

    @URL
    @NotNull
    @Column(name = "url")
    @ApiModelProperty(value = "URL", required = true)
    private String url;
}
