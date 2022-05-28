package com.br.empresa.pilots.qualify;

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
@RequestMapping("/api/qualify")
@AllArgsConstructor
@Api(value = "Qualify")
@Transactional
public class QualifyingController {

    @Autowired
    QualifyingRepository qualifyRepository;

    @ApiOperation(value = "Return a list of qualifys")
    @GetMapping
    public ResponseEntity<List<Qualifying>> listAll() {
        List<Qualifying> qualifyList = qualifyRepository.findAll();
        if (qualifyList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Qualifying qualify : qualifyList) {
                long id = qualify.getId();
                qualify.add(linkTo(methodOn(QualifyingController.class).singleQualify(id)).withSelfRel());
            }
            return new ResponseEntity<List<Qualifying>>(qualifyList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a single qualify")
    @GetMapping("/{id}")
    public ResponseEntity<Qualifying> singleQualify(@PathVariable(value = "id") long id) {
        Optional<Qualifying> qualifyO = Optional.ofNullable(qualifyRepository.findById(id));
        if (!qualifyO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            qualifyO.get().add(linkTo(methodOn(QualifyingController.class).listAll()).withRel("List of qualifys"));
            return new ResponseEntity<Qualifying>(qualifyO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save a qualify")
    @PostMapping
    public ResponseEntity<Qualifying> saveQualify(@RequestBody @Validated Qualifying qualify) {
        return new ResponseEntity<Qualifying>(qualifyRepository.save(qualify), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a qualify")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQualify(@PathVariable(value = "id") long id){
        Optional<Qualifying> qualifyO = Optional.ofNullable(qualifyRepository.findById(id));
        if (!qualifyO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            qualifyRepository.delete(qualifyO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a qualify")
    @PutMapping("/{id}")
    public ResponseEntity<Qualifying> updateQualify(@PathVariable(value="id") long id,
                                                    @RequestBody @Validated Qualifying qualify) {
        Optional<Qualifying> qualifyO = Optional.ofNullable(qualifyRepository.findById(id));
        if(!qualifyO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        qualify.setId(qualifyO.get().getId());
        return new ResponseEntity<Qualifying>(qualifyRepository.save(qualify), HttpStatus.OK);
    }

}
