package com.br.empresa.pilots.qualifying;

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
@RequestMapping("/api/qualifying")
@AllArgsConstructor
@Api(value = "Qualify")
@Transactional
public class QualifyingController {

    @Autowired
    QualifyingRepository qualifyingRepository;

    @ApiOperation(value = "Return a list of qualifying")
    @GetMapping
    public ResponseEntity<List<Qualifying>> listAll() {
        List<Qualifying> qualifyingList = qualifyingRepository.findAll();
        if (qualifyingList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Qualifying qualifying : qualifyingList) {
                long id = qualifying.getId();
                qualifying.add(linkTo(methodOn(QualifyingController.class).singleQualifying(id)).withSelfRel());
            }
            return new ResponseEntity<List<Qualifying>>(qualifyingList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a single qualifying")
    @GetMapping("/{id}")
    public ResponseEntity<Qualifying> singleQualifying(@PathVariable(value = "id") long id) {
        Optional<Qualifying> qualifyO = Optional.ofNullable(qualifyingRepository.findById(id));
        if (!qualifyO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            qualifyO.get().add(linkTo(methodOn(QualifyingController.class).listAll()).withRel("List of qualifyings"));
            return new ResponseEntity<Qualifying>(qualifyO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save a qualifying")
    @PostMapping
    public ResponseEntity<Qualifying> saveQualifying(@RequestBody @Validated Qualifying qualifying) {
        return new ResponseEntity<Qualifying>(qualifyingRepository.save(qualifying), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a qualifying")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQualifying(@PathVariable(value = "id") long id){
        Optional<Qualifying> qualifyingO = Optional.ofNullable(qualifyingRepository.findById(id));
        if (!qualifyingO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            qualifyingRepository.delete(qualifyingO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a qualify")
    @PutMapping("/{id}")
    public ResponseEntity<Qualifying> updateQualifying(@PathVariable(value="id") long id,
                                                       @RequestBody @Validated Qualifying qualifying) {
        Optional<Qualifying> qualifyingO = Optional.ofNullable(qualifyingRepository.findById(id));
        if(!qualifyingO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        qualifying.setId(qualifyingO.get().getId());
        return new ResponseEntity<Qualifying>(qualifyingRepository.save(qualifying), HttpStatus.OK);
    }

}
