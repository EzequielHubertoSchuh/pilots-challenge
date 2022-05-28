package com.br.empresa.pilots.status;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(value  = "Status", description = "This is a sample entity from the Api package.")
@Table(name = "tb_status")
public class Status extends RepresentationModel<Status> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "statusid")
    private Long id;

    @ApiModelProperty(value = "Status", required = true)
    @NotBlank
    @Column(name = "status", nullable = false)
    private String status;
}
