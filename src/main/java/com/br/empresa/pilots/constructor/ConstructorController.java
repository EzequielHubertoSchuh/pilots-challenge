package com.br.empresa.pilots.constructor;

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
public class ConstructorController {

    @Autowired
    ConstructorRepository constructorRepository;

    @ApiOperation(value = "Returns a list of constructors")
    @GetMapping
    public ResponseEntity<List<Constructor>> listAll() {
        List<Constructor> constructorList = constructorRepository.findAll();
        if (constructorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Constructor constructor : constructorList) {
                long id = constructor.getId();
                constructor.add(linkTo(methodOn(ConstructorController.class).singleConstructor(id)).withSelfRel());
            }
            return new ResponseEntity<List<Constructor>>(constructorList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a single constructor")
    @GetMapping("/{id}")
    public ResponseEntity<Constructor> singleConstructor(@PathVariable(value = "id") long id) {
        Optional<Constructor> constructorO = Optional.ofNullable(constructorRepository.findById(id));
        if (!constructorO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            constructorO.get().add(linkTo(methodOn(ConstructorController.class).listAll()).withRel("List of constructors"));
            return new ResponseEntity<Constructor>(constructorO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save a constructor")
    @PostMapping
    public ResponseEntity<Constructor> saveConstructor(@RequestBody @Validated Constructor constructor) {
        return new ResponseEntity<Constructor>(constructorRepository.save(constructor), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a constructor")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConstructor(@PathVariable(value = "id") long id){
        Optional<Constructor> constructorO = Optional.ofNullable(constructorRepository.findById(id));
        if (!constructorO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            constructorRepository.delete(constructorO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a constructor")
    @PutMapping("/{id}")
    public ResponseEntity<Constructor> updateConstructor(@PathVariable(value="id") long id,
                                                    @RequestBody @Validated Constructor constructor) {
        Optional<Constructor> constructorO = Optional.ofNullable(constructorRepository.findById(id));
        if(!constructorO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        constructor.setId(constructorO.get().getId());
        return new ResponseEntity<Constructor>(constructorRepository.save(constructor), HttpStatus.OK);
    }

}
