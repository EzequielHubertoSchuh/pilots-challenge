package com.br.empresa.pilots.status;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ApiModel(value  = "Status", description = "This is a sample entity from the Api package.")
@Table(name = "tb_status")
@SequenceGenerator(name = "seq_status", sequenceName = "seq_status", allocationSize = 1)
public class Status extends RepresentationModel<Status> {

    @Id
    @GeneratedValue(generator = "seq_status", strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "statusid")
    private Long id;

    @ApiModelProperty(value = "Status", required = true)
    @NotBlank
    @Column(name = "status", nullable = false)
    private String status;
}
