package com.br.empresa.pilots.laptimes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/api/laptimes")
@AllArgsConstructor
@Api(value = "Lap Times")
@Transactional
public class LapTimesController {

    @Autowired
    LapTimesRepository lapTimesRepository;

    @ApiOperation(value = "Return list of lap times")
    @GetMapping
    public ResponseEntity<List<LapTimes>> listAll() {
        List<LapTimes> lapTimesList = lapTimesRepository.findAll();
        if (lapTimesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (LapTimes lapTimes : lapTimesList) {
                long id = lapTimes.getId();
                lapTimes.add(linkTo(methodOn(LapTimesController.class).singleLapTimes(id)).withSelfRel());
            }
            return new ResponseEntity<List<LapTimes>>(lapTimesList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return single lap times")
    @GetMapping("/{id}")
    public ResponseEntity<LapTimes> singleLapTimes(@PathVariable(value = "id") long id) {
        Optional<LapTimes> lapTimesO = Optional.ofNullable(lapTimesRepository.findById(id));
        if (!lapTimesO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            lapTimesO.get().add(linkTo(methodOn(LapTimesController.class).listAll()).withRel("List of lap times"));
            return new ResponseEntity<LapTimes>(lapTimesO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save lap times")
    @PostMapping
    public ResponseEntity<LapTimes> saveLapTimes(@RequestBody @Validated LapTimes lapTimes) {
        return new ResponseEntity<LapTimes>(lapTimesRepository.save(lapTimes), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a lap times")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLapTimes(@PathVariable(value = "id") long id){
        Optional<LapTimes> lapTimesO = Optional.ofNullable(lapTimesRepository.findById(id));
        if (!lapTimesO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            lapTimesRepository.delete(lapTimesO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update lap times")
    @PutMapping("/{id}")
    public ResponseEntity<LapTimes> updateLpaTimes(@PathVariable(value="id") long id,
                                                   @RequestBody @Validated LapTimes lapTimes) {
        Optional<LapTimes> lapTimesO = Optional.ofNullable(lapTimesRepository.findById(id));
        if(!lapTimesO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lapTimes.setId(lapTimesO.get().getId());
        return new ResponseEntity<LapTimes>(lapTimesRepository.save(lapTimes), HttpStatus.OK);
    }

    @ApiOperation(value = "Return a list of lap times order by race")
    @GetMapping(value = "/sortByRace")
    public ResponseEntity<List<LapTimes>> findOrderByRaceid() {
        List<LapTimes> lapTimesList = lapTimesRepository.findAll(Sort.by(Sort.Direction.ASC, "raceid"));
        if (lapTimesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (LapTimes lapTimes : lapTimesList) {
                long id = lapTimes.getId();
                lapTimes.add(linkTo(methodOn(LapTimesController.class).singleLapTimes(id)).withSelfRel());
            }
            return new ResponseEntity<List<LapTimes>>(lapTimesList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a list of books order by race and lap")
    @GetMapping(value = "/sortByRaceAndLap")
    public ResponseEntity<List<LapTimes>> findOrderByRaceAndLap() {
        Sort sort = Sort.by(Sort.Order.asc("raceid"), Sort.Order.desc("lap"));
        List<LapTimes> lapTimesList = lapTimesRepository.findAll(sort);
        if (lapTimesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (LapTimes lapTimes : lapTimesList) {
                long id = lapTimes.getId();
                lapTimes.add(linkTo(methodOn(LapTimesController.class).singleLapTimes(id)).withSelfRel());
            }
            return new ResponseEntity<List<LapTimes>>(lapTimesList, HttpStatus.OK);
        }
    }

}
