package com.br.empresa.pilots.constructorresult;

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
@RequestMapping("/api/constructors")
@AllArgsConstructor
@Api(value = "Constructor")
@Transactional
public class ConstructorResultController {

    @Autowired
    ConstructorResultRepository constructorRepository;

    @ApiOperation(value = "Returns a list of constructors result")
    @GetMapping
    public ResponseEntity<List<ConstructorResult>> listAll() {
        List<ConstructorResult> constructorResultList = constructorRepository.findAll();
        if (constructorResultList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (ConstructorResult constructorResult : constructorResultList) {
                long id = constructorResult.getId();
                constructorResult.add(linkTo(methodOn(ConstructorResultController.class).singleConstructorResult(id)).withSelfRel());
            }
            return new ResponseEntity<List<ConstructorResult>>(constructorResultList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a single constructor result")
    @GetMapping("/{id}")
    public ResponseEntity<ConstructorResult> singleConstructorResult(@PathVariable(value = "id") long id) {
        Optional<ConstructorResult> constructorResultO = Optional.ofNullable(constructorRepository.findById(id));
        if (!constructorResultO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            constructorResultO.get().add(linkTo(methodOn(ConstructorResultController.class).listAll()).withRel("List of constructors"));
            return new ResponseEntity<ConstructorResult>(constructorResultO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save a constructor result")
    @PostMapping
    public ResponseEntity<ConstructorResult> saveConstructor(@RequestBody @Validated ConstructorResult constructor) {
        return new ResponseEntity<ConstructorResult>(constructorRepository.save(constructor), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a constructor result")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConstructorResult(@PathVariable(value = "id") long id){
        Optional<ConstructorResult> constructorResultO = Optional.ofNullable(constructorRepository.findById(id));
        if (!constructorResultO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            constructorRepository.delete(constructorResultO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a constructor result")
    @PutMapping("/{id}")
    public ResponseEntity<ConstructorResult> updateConstructorResult(@PathVariable(value="id") long id,
                                                                     @RequestBody @Validated ConstructorResult constructorResult) {
        Optional<ConstructorResult> constructorResultO = Optional.ofNullable(constructorRepository.findById(id));
        if(!constructorResultO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        constructorResult.setId(constructorResultO.get().getId());
        return new ResponseEntity<ConstructorResult>(constructorRepository.save(constructorResult), HttpStatus.OK);
    }

}
