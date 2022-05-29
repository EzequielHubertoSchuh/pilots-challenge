package com.br.empresa.pilots.constructor;

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
public class Constructor extends RepresentationModel<Constructor> {

    @ApiModelProperty(value = "Identifier")
    @Id  @GeneratedValue(generator = "seq_constructor", strategy = GenerationType.SEQUENCE)
    @Column(name = "constructorid")
    private Long id;

    @NotNull
    @Column(name = "constructorref")
    @ApiModelProperty(value = "constructorRef", required = true)
    private String constructorRef;

    @ApiModelProperty(value = "name", required = true)
    @NotBlank
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(name = "nationality")
    @ApiModelProperty(value = "nationality", required = true)
    private String nationality;

    @URL
    @NotNull
    @Column(name = "url")
    @ApiModelProperty(value = "url", required = true)
    private String url;

}
