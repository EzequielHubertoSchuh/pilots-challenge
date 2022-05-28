package com.br.empresa.pilots.season;

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
@RequestMapping("/api/season")
@AllArgsConstructor
@Api(value = "Season")
@Transactional
public class SeasonController {
    @Autowired
    SeasonRepository seasonRepository;

    @ApiOperation(value = "Return a list of seasons")
    @GetMapping
    public ResponseEntity<List<Season>> listAll() {
        List<Season> seasonList = seasonRepository.findAll();
        if (seasonList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Season season : seasonList) {
                long id = season.getId();
                season.add(linkTo(methodOn(SeasonController.class).singleSeason(id)).withSelfRel());
            }
            return new ResponseEntity<List<Season>>(seasonList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a single season")
    @GetMapping("/{id}")
    public ResponseEntity<Season> singleSeason(@PathVariable(value = "id") long id) {
        Optional<Season> seasonO = Optional.ofNullable(seasonRepository.findById(id));
        if (!seasonO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            seasonO.get().add(linkTo(methodOn(SeasonController.class).listAll()).withRel("List of seasons"));
            return new ResponseEntity<Season>(seasonO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save season")
    @PostMapping
    public ResponseEntity<Season> saveSeason(@RequestBody @Validated Season season) {
        return new ResponseEntity<Season>(seasonRepository.save(season), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete season")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeason(@PathVariable(value = "id") long id){
        Optional<Season> seasonO = Optional.ofNullable(seasonRepository.findById(id));
        if (!seasonO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            seasonRepository.delete(seasonO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update season")
    @PutMapping("/{id}")
    public ResponseEntity<Season> updateCircuit(@PathVariable(value="id") long id,
                                                 @RequestBody @Validated Season season) {
        Optional<Season> seasonO = Optional.ofNullable(seasonRepository.findById(id));
        if(!seasonO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        season.setId(seasonO.get().getId());
        return new ResponseEntity<Season>(seasonRepository.save(season), HttpStatus.OK);
    }
}
