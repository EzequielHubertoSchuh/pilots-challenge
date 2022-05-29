package com.br.empresa.pilots.race;


import com.br.empresa.pilots.circuit.Circuit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ApiModel(value  = "Race", description = "This is a sample entity from the Api package.")
@Table(name = "tb_race")
@SequenceGenerator(name = "seq_race", sequenceName = "seq_race", allocationSize = 1)
public class Race extends RepresentationModel<Race> {


    @Id @GeneratedValue(generator = "seq_race", strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(value = "Identifier")
    @Column(name = "raceid")
    private Long id;

    @Column(name = "year")
    @NotNull(message = "Year field is required")
    @ApiModelProperty(value = "Year", required = true)
    private Integer year;

    @Column(name = "round")
    @NotNull(message = "Round field is required")
    @ApiModelProperty(value = "Round", required = true)
    private Integer round;

    @ApiModelProperty(value = "Circuit", required = true)
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "circuitid")
    private Circuit circuit;

    @ApiModelProperty(value = "Name", required = true)
    @NotNull(message = "Name field is required")
    @Column(name = "name")
    private Integer name;

    @ApiModelProperty(value = "Date", required = true)
    @NotNull(message = "Date field is required")
    @Column(name = "date")
    private LocalDate date;

    @ApiModelProperty(value = "Time", required = true)
    @Column(name = "time")
    private LocalTime time;

    @URL
    @NotNull(message = "Url field is required")
    @Column(name = "url")
    @ApiModelProperty(value = "URL", required = true)
    private String url;

    @ApiModelProperty(value = "fp1Date", required = true)
    @NotNull(message = "fp1Date field is required")
    @Column(name = "fp1date")
    private LocalDate fp1Date;

    @ApiModelProperty(value = "fp1Time", required = true)
    @Column(name = "fp1time")
    private String fp1Time;

    @ApiModelProperty(value = "fp2Date", required = true)
    @NotNull(message = "fp2Date field is required")
    @Column(name = "fp2date")
    private LocalDate fp2Date;

    @ApiModelProperty(value = "fp2Time", required = true)
    @Column(name = "fp2time")
    private String fp2Time;

    @ApiModelProperty(value = "fp3Date", required = true)
    @NotNull(message = "fp3Date field is required")
    @Column(name = "fp3date")
    private LocalDate fp3Date;

    @ApiModelProperty(value = "fp3Time", required = true)
    @Column(name = "fp3time")
    private String fp3Time;

    @ApiModelProperty(value = "qualiDate", required = true)
    @NotNull(message = "qualiDate field is required")
    @Column(name = "qualidate")
    private LocalDate qualiDate;

    @ApiModelProperty(value = "qualiTime", required = true)
    @Column(name = "qualitime")
    private String qualiTime;

    @ApiModelProperty(value = "sprintDate", required = true)
    @NotNull(message = "sprintDate field is required")
    @Column(name = "sprintdate")
    private LocalDate sprintDate;

    @ApiModelProperty(value = "sprintTime", required = true)
    @Column(name = "sprinttime")
    private String sprintTime;

}
