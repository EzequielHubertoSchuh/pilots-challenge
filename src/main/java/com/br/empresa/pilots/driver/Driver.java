package com.br.empresa.pilots.driver;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
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
@Builder
@Entity
@ApiModel(value  = "Driver", description = "This is a sample entity from the Api package.")
@Table(name = "tb_driver")
@SequenceGenerator(name = "seq_driver", sequenceName = "seq_driver", allocationSize = 1)
public class Driver extends RepresentationModel<Driver> {

    @Id @GeneratedValue(generator = "seq_driver",strategy = GenerationType.SEQUENCE)
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

    @Size(min = 3,max = 3, message = "Max 3 caracteres")
    @Column(name = "code")
    @ApiModelProperty(value = "code")
    private String code;

    @NotNull(message="Forename cannot be null")
    @Column(name = "forename")
    @ApiModelProperty(value = "forename", required = true)
    private String forename;

    @NotNull(message="Surname cannot be null")
    @Column(name = "surname")
    @ApiModelProperty(value = "surname", required = true)
    private String surname;

    @Column(name = "dob")
    @ApiModelProperty(value = "dob")
    private LocalDateTime dob;

    @NotNull(message="Nationality cannot be null")
    @Column(name = "nationality")
    @ApiModelProperty(value = "nationality", required = true)
    private String nationality;

    @URL
    @NotNull(message="U.R.L cannot be null")
    @Column(name = "url")
    @ApiModelProperty(value = "url", required = true)
    private String url;

    public Driver(Long id, String driverRef) {
        this.id = id;
        this.driverRef = driverRef;
    }
}
