package com.br.empresa.pilots.race;

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
@RequestMapping("/api/race")
@AllArgsConstructor
@Api(value = "Race")
@Transactional
public class RaceController {
    @Autowired
    RaceRepository raceRepository;

    @ApiOperation(value = "Return list of races")
    @GetMapping
    public ResponseEntity<List<Race>> listAll() {
        List<Race> raceList = raceRepository.findAll();
        if (raceList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Race race : raceList) {
                long id = race.getId();
                race.add(linkTo(methodOn(RaceController.class).singleRace(id)).withSelfRel());
            }
            return new ResponseEntity<List<Race>>(raceList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return single race")
    @GetMapping("/{id}")
    public ResponseEntity<Race> singleRace(@PathVariable(value = "id") long id) {
        Optional<Race> raceO = Optional.ofNullable(raceRepository.findById(id));
        if (!raceO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            raceO.get().add(linkTo(methodOn(RaceController.class).listAll()).withRel("List of races"));
            return new ResponseEntity<Race>(raceO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save race")
    @PostMapping
    public ResponseEntity<Race> saveRace(@RequestBody @Validated Race race) {
        return new ResponseEntity<Race>(raceRepository.save(race), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete race")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRace(@PathVariable(value = "id") long id){
        Optional<Race> raceO = Optional.ofNullable(raceRepository.findById(id));
        if (!raceO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            raceRepository.delete(raceO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update race")
    @PutMapping("/{id}")
    public ResponseEntity<Race> updateRace(@PathVariable(value="id") long id,
                                              @RequestBody @Validated Race race) {
        Optional<Race> raceO = Optional.ofNullable(raceRepository.findById(id));
        if(!raceO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        race.setId(raceO.get().getId());
        return new ResponseEntity<Race>(raceRepository.save(race), HttpStatus.OK);
    }
}
