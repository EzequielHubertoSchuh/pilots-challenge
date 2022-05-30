package com.br.empresa.pilots.pitstop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pitstops")
@AllArgsConstructor
@Api(value = "Pit Stops")
@Transactional
public class PitStopController {

    @Autowired
    PitStopRepository pitStopRepository;

    @ApiOperation(value = "Return list of pit stops")
    @GetMapping
    public ResponseEntity<List<PitStop>> listAll() {
        List<PitStop> pitStopList = pitStopRepository.findAll();
        if (pitStopList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (PitStop pitStop : pitStopList) {
                long id = pitStop.getId();
                pitStop.add(linkTo(methodOn(PitStopController.class).singlePitStop(id)).withSelfRel());
            }
            return new ResponseEntity<List<PitStop>>(pitStopList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return single pit stops")
    @GetMapping("/{id}")
    public ResponseEntity<PitStop> singlePitStop(@PathVariable(value = "id") long id) {
        Optional<PitStop> pitstopO = Optional.ofNullable(pitStopRepository.findById(id));
        if (!pitstopO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            pitstopO.get().add(linkTo(methodOn(PitStopController.class).listAll()).withRel("List of pit stops"));
            return new ResponseEntity<PitStop>(pitstopO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save pit stop")
    @PostMapping
    public ResponseEntity<PitStop> savePitStops(@RequestBody @Validated PitStop lapTimes) {
        return new ResponseEntity<PitStop>(pitStopRepository.save(lapTimes), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete pit stop")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePitStop(@PathVariable(value = "id") long id){
        Optional<PitStop> pitstopO = Optional.ofNullable(pitStopRepository.findById(id));
        if (!pitstopO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            pitStopRepository.delete(pitstopO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update pit stop")
    @PutMapping("/{id}")
    public ResponseEntity<PitStop> updatePitStop(@PathVariable(value="id") long id,
                                                 @RequestBody @Validated PitStop pitStop) {
        Optional<PitStop> pitstopO = Optional.ofNullable(pitStopRepository.findById(id));
        if(!pitstopO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pitStop.setId(pitstopO.get().getId());
        return new ResponseEntity<PitStop>(pitStopRepository.save(pitStop), HttpStatus.OK);
    }

}
