package com.br.empresa.pilots.driverstanding;

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
@RequestMapping("/api/driverstanding")
@AllArgsConstructor
@Api(value = "Driver Standing")
@Transactional
public class DriverStandingControlller {
    @Autowired
    DriverStandingRepository driverStandingRepository;

    @ApiOperation(value = "Return a list of drivers")
    @GetMapping
    public ResponseEntity<List<DriverStanding>> listAll() {
        List<DriverStanding> driverStandingList = driverStandingRepository.findAll();
        if (driverStandingList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (DriverStanding driverStanding : driverStandingList) {
                long id = driverStanding.getId();
                driverStanding.add(linkTo(methodOn(DriverStandingControlller.class).singleDriverStanding(id)).withSelfRel());
            }
            return new ResponseEntity<List<DriverStanding>>(driverStandingList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a single driver standing")
    @GetMapping("/{id}")
    public ResponseEntity<DriverStanding> singleDriverStanding(@PathVariable(value = "id") long id) {
        Optional<DriverStanding> driverStandingO = Optional.ofNullable(driverStandingRepository.findById(id));
        if (!driverStandingO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            driverStandingO.get().add(linkTo(methodOn(DriverStandingControlller.class).listAll()).withRel("List of driver standings"));
            return new ResponseEntity<DriverStanding>(driverStandingO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save a driver standing")
    @PostMapping
    public ResponseEntity<DriverStanding> saveDriverStanding(@RequestBody @Validated DriverStanding driverStanding) {
        return new ResponseEntity<DriverStanding>(driverStandingRepository.save(driverStanding), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a driver standig")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriverStanding(@PathVariable(value = "id") long id){
        Optional<DriverStanding> driverStandingO = Optional.ofNullable(driverStandingRepository.findById(id));
        if (!driverStandingO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            driverStandingRepository.delete(driverStandingO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a driver standing")
    @PutMapping("/{id}")
    public ResponseEntity<DriverStanding> updateDriverStanding(@PathVariable(value="id") long id,
                                               @RequestBody @Validated DriverStanding driverStanding) {
        Optional<DriverStanding> driverStandingO = Optional.ofNullable(driverStandingRepository.findById(id));
        if(!driverStandingO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        driverStanding.setId(driverStandingO.get().getId());
        return new ResponseEntity<DriverStanding>(driverStandingRepository.save(driverStanding), HttpStatus.OK);
    }
}
