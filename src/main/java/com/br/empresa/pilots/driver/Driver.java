package com.br.empresa.pilots.driver;


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
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(value  = "Driver", description = "This is a sample entity from the Api package.")
@Table(name = "tb_driver")
public class Driver extends RepresentationModel<Driver> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "driverid")
    private Long id;

    @NotNull(message = "Mandatory driver reference field")
    @Column(name = "driverref")
    @ApiModelProperty(value = "driverRef", required = true)
    private String driverRef;

    @Column(name = "number")
    @ApiModelProperty(value = "number")
    private Integer number;

    @Size(min = 3,max = 3, message = "Maximo 3 caracteres")
    @Column(name = "code")
    @ApiModelProperty(value = "code")
    private String code;

    @NotNull
    @Column(name = "forename")
    @ApiModelProperty(value = "forename", required = true)
    private String forename;

    @NotNull
    @Column(name = "surname")
    @ApiModelProperty(value = "surname", required = true)
    private String surname;

    @Column(name = "dob")
    @ApiModelProperty(value = "dob")
    private LocalDateTime dob;

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
