
package com.br.empresa.pilots.product;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
@Api(value = "Product")
@Transactional
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @ApiOperation(value = "Returns a page of products")
    @RequestMapping(method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(Pageable pageable){
        return new ResponseEntity<>(productRepository.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns a list of products")
    @RequestMapping(value= "/all", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAll(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Save a product")
    @PostMapping
    public ResponseEntity<Product> saveCourse(@RequestBody @Validated Product product) {
        return new ResponseEntity<Product>(productRepository.save(product), HttpStatus.CREATED);
    }

}

