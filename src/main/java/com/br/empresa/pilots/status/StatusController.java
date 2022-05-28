package com.br.empresa.pilots.status;

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
@RequestMapping("/api/status")
@AllArgsConstructor
@Api(value = "Status")
@Transactional
public class StatusController {
    @Autowired
    StatusRepository seasonRepository;

    @ApiOperation(value = "Return a list of status")
    @GetMapping
    public ResponseEntity<List<Status>> listAll() {
        List<Status> statusList = seasonRepository.findAll();
        if (statusList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Status status : statusList) {
                long id = status.getId();
                status.add(linkTo(methodOn(StatusController.class).singleStatus(id)).withSelfRel());
            }
            return new ResponseEntity<List<Status>>(statusList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Order by status")
    @GetMapping(value = "/sortByStatus")
    public ResponseEntity<List<Status>> findOrderByStatus() {
        List<Status> statusList = seasonRepository.findAll(Sort.by(Sort.Direction.ASC, "status"));
        if (statusList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Status status : statusList) {
                long id = status.getId();
                status.add(linkTo(methodOn(StatusController.class).singleStatus(id)).withSelfRel());
            }
            return new ResponseEntity<List<Status>>(statusList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a single status")
    @GetMapping("/{id}")
    public ResponseEntity<Status> singleStatus(@PathVariable(value = "id") long id) {
        Optional<Status> statusO = Optional.ofNullable(seasonRepository.findById(id));
        if (!statusO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            statusO.get().add(linkTo(methodOn(StatusController.class).listAll()).withRel("List of status"));
            return new ResponseEntity<Status>(statusO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save a season")
    @PostMapping
    public ResponseEntity<Status> saveStatus(@RequestBody @Validated Status status) {
        return new ResponseEntity<Status>(seasonRepository.save(status), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a status")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable(value = "id") long id){
        Optional<Status> statusO = Optional.ofNullable(seasonRepository.findById(id));
        if (!statusO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            seasonRepository.delete(statusO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update a status")
    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable(value="id") long id,
                                               @RequestBody @Validated Status status) {
        Optional<Status> statusO = Optional.ofNullable(seasonRepository.findById(id));
        if(!statusO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        status.setId(statusO.get().getId());
        return new ResponseEntity<Status>(seasonRepository.save(status), HttpStatus.OK);
    }

}
