package com.br.empresa.pilots.result;

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
@RequestMapping("/api/results")
@AllArgsConstructor
@Api(value = "Results")
@Transactional
public class ResultController {

    @Autowired
    ResultRepository resultRepository;

    @ApiOperation(value = "Return list of results")
    @GetMapping
    public ResponseEntity<List<Result>> listAll() {
        List<Result> resultList = resultRepository.findAll();
        if (resultList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Result result : resultList) {
                long id = result.getId();
                result.add(linkTo(methodOn(ResultController.class).singleResult(id)).withSelfRel());
            }
            return new ResponseEntity<List<Result>>(resultList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return single results")
    @GetMapping("/{id}")
    public ResponseEntity<Result> singleResult(@PathVariable(value = "id") long id) {
        Optional<Result> resultO = Optional.ofNullable(resultRepository.findById(id));
        if (!resultO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            resultO.get().add(linkTo(methodOn(ResultController.class).listAll()).withRel("List of results"));
            return new ResponseEntity<Result>(resultO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save results")
    @PostMapping
    public ResponseEntity<Result> saveResult(@RequestBody @Validated Result lapTimes) {
        return new ResponseEntity<Result>(resultRepository.save(lapTimes), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete result")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResult(@PathVariable(value = "id") long id){
        Optional<Result> resultO = Optional.ofNullable(resultRepository.findById(id));
        if (!resultO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            resultRepository.delete(resultO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update result")
    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable(value="id") long id,
                                               @RequestBody @Validated Result result) {
        Optional<Result> resultO = Optional.ofNullable(resultRepository.findById(id));
        if(!resultO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        result.setId(resultO.get().getId());
        return new ResponseEntity<Result>(resultRepository.save(result), HttpStatus.OK);
    }

}
