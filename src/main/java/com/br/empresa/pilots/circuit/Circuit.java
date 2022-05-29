package com.br.empresa.pilots.circuit;

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
@ApiModel(value  = "Circuit", description = "This is a sample entity from the Api package.")
@Table(name = "tb_circuit")
@SequenceGenerator(name = "seq_circuit", sequenceName = "seq_circuit", allocationSize = 1)
public class Circuit extends RepresentationModel<Circuit> {

    @Id @GeneratedValue(generator = "seq_circuit", strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "circuitid")
    private Long id;

    @ApiModelProperty(value = "Circuit reference", required = true)
    @NotNull
    @Column(name = "circuitref")
    private String circuitRef;

    @ApiModelProperty(value = "Name", required = true)
    @NotNull
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "Location", required = true)
    @NotNull
    @Column(name = "location")
    private String location;

    @ApiModelProperty(value = "Country", required = true)
    @NotNull
    @Column(name = "country")
    private String country;

    @ApiModelProperty(value = "Latitude", required = true)
    @NotNull
    @Column(name = "lat")
    private Double lat;

    @ApiModelProperty(value = "Longitude", required = true)
    @NotNull
    @Column(name = "lng")
    private Double lng;

    @ApiModelProperty(value = "Height")
    @Column(name = "alt")
    private Integer alt;

    @ApiModelProperty(value = "URL", required = true)
    @URL
    @NotNull
    @Column(name = "url")
    private String url;
}
