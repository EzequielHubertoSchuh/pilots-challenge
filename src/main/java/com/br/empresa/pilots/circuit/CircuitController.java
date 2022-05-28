package com.br.empresa.pilots.circuit;

import com.br.empresa.pilots.constructor.ConstructorController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
@RequestMapping("/api/circuit")
@AllArgsConstructor
@Api(value = "Circuit")
@Transactional
public class CircuitController {
    @Autowired
    CircuitRepository circuitRepository;

    @ApiOperation(value = "Return a list of circuits")
    @GetMapping
    public ResponseEntity<List<Circuit>> listAll() {
        List<Circuit> circuitList = circuitRepository.findAll();
        if (circuitList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Circuit circuit : circuitList) {
                long id = circuit.getId();
                circuit.add(linkTo(methodOn(CircuitController.class).singleCircuit(id)).withSelfRel());
            }
            return new ResponseEntity<List<Circuit>>(circuitList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a single circuit")
    @GetMapping("/{id}")
    public ResponseEntity<Circuit> singleCircuit(@PathVariable(value = "id") long id) {
        Optional<Circuit> circuitO = Optional.ofNullable(circuitRepository.findById(id));
        if (!circuitO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            circuitO.get().add(WebMvcLinkBuilder.linkTo(methodOn(ConstructorController.class).listAll()).withRel("List of circuits"));
            return new ResponseEntity<Circuit>(circuitO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save a circuit")
    @PostMapping
    public ResponseEntity<Circuit> saveCircuit(@RequestBody @Validated Circuit circuit) {
        return new ResponseEntity<Circuit>(circuitRepository.save(circuit), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a circuit")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCircuit(@PathVariable(value = "id") long id){
        Optional<Circuit> circuitO = Optional.ofNullable(circuitRepository.findById(id));
        if (!circuitO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            circuitRepository.delete(circuitO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a circuit")
    @PutMapping("/{id}")
    public ResponseEntity<Circuit> updateCircuit(@PathVariable(value="id") long id,
                                               @RequestBody @Validated Circuit circuit) {
        Optional<Circuit> circuitO = Optional.ofNullable(circuitRepository.findById(id));
        if(!circuitO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        circuit.setId(circuitO.get().getId());
        return new ResponseEntity<Circuit>(circuitRepository.save(circuit), HttpStatus.OK);
    }
}
