package com.br.empresa.pilots.driver;


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
@RequestMapping("/api/drivers")
@AllArgsConstructor
@Api(value = "Driver")
@Transactional
public class DriverController {
    @Autowired
    DriverRepository driverRepository;

    @ApiOperation(value = "Return a list of drivers")
    @GetMapping
    public ResponseEntity<List<Driver>> listAll() {
        List<Driver> driverList = driverRepository.findAll();
        if (driverList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Driver driver : driverList) {
                long id = driver.getId();
                driver.add(linkTo(methodOn(DriverController.class).singleDriver(id)).withSelfRel());
            }
            return new ResponseEntity<List<Driver>>(driverList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a single driver")
    @GetMapping("/{id}")
    public ResponseEntity<Driver> singleDriver(@PathVariable(value = "id") long id) {
        Optional<Driver> driverO = Optional.ofNullable(driverRepository.findById(id));
        if (!driverO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            driverO.get().add(linkTo(methodOn(DriverController.class).listAll()).withRel("List of drivers"));
            return new ResponseEntity<Driver>(driverO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save a driver")
    @PostMapping
    public ResponseEntity<Driver> saveDriver(@RequestBody @Validated Driver driver) {
        return new ResponseEntity<Driver>(driverRepository.save(driver), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a driver")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable(value = "id") long id){
        Optional<Driver> driverO = Optional.ofNullable(driverRepository.findById(id));
        if (!driverO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            driverRepository.delete(driverO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a driver")
    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable(value="id") long id,
                                               @RequestBody @Validated Driver driver) {
        Optional<Driver> driverO = Optional.ofNullable(driverRepository.findById(id));
        if(!driverO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        driver.setId(driverO.get().getId());
        return new ResponseEntity<Driver>(driverRepository.save(driver), HttpStatus.OK);
    }
}
