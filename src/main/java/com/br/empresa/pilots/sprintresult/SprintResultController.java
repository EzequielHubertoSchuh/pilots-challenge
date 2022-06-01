package com.br.empresa.pilots.sprintresult;

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
@RequestMapping("/api/sprintresult")
@AllArgsConstructor
@Api(value = "Sprint result")
@Transactional
public class SprintResultController {

    @Autowired
    SprintRepository sprintRepository;

    @ApiOperation(value = "Return a list of sprint results")
    @GetMapping
    public ResponseEntity<List<SprintResult>> listAll() {
        List<SprintResult> sprintResultList = sprintRepository.findAll();
        if (sprintResultList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (SprintResult sprintResult : sprintResultList) {
                long id = sprintResult.getId();
                sprintResult.add(linkTo(methodOn(SprintResultController.class).singleSprintResult(id)).withSelfRel());
            }
            return new ResponseEntity<List<SprintResult>>(sprintResultList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a single sprint result")
    @GetMapping("/{id}")
    public ResponseEntity<SprintResult> singleSprintResult(@PathVariable(value = "id") long id) {
        Optional<SprintResult> sprintResultO = Optional.ofNullable(sprintRepository.findById(id));
        if (!sprintResultO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            sprintResultO.get().add(linkTo(methodOn(SprintResultController.class).listAll()).withRel("List of sprint result"));
            return new ResponseEntity<SprintResult>(sprintResultO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save a sprint result")
    @PostMapping
    public ResponseEntity<SprintResult> saveSprintResult(@RequestBody @Validated SprintResult sprintResult) {
        return new ResponseEntity<SprintResult>(sprintRepository.save(sprintResult), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a sprint result")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSprintResult(@PathVariable(value = "id") long id){
        Optional<SprintResult> sprintResultO = Optional.ofNullable(sprintRepository.findById(id));
        if (!sprintResultO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            sprintRepository.delete(sprintResultO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a sprint result")
    @PutMapping("/{id}")
    public ResponseEntity<SprintResult> updateSprintResult(@PathVariable(value="id") long id,
                                                           @RequestBody @Validated SprintResult sprintResult) {
        Optional<SprintResult> printResultO = Optional.ofNullable(sprintRepository.findById(id));
        if(!printResultO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sprintResult.setId(printResultO.get().getId());
        return new ResponseEntity<SprintResult>(sprintRepository.save(sprintResult), HttpStatus.OK);
    }

}
