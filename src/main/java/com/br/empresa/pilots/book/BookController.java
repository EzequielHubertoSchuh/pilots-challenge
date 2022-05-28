package com.br.empresa.pilots.book;

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
@RequestMapping("/api/books")
@AllArgsConstructor
@Api(value = "Book")
@Transactional
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @ApiOperation(value = "Return a list of books")
    @GetMapping
    public ResponseEntity<List<Book>> listAll() {
        List<Book> bookList = bookRepository.findAll();
        if (bookList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Book book : bookList) {
                long id = book.getId();
                book.add(linkTo(methodOn(BookController.class).singleBook(id)).withSelfRel());
            }
            return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a single book")
    @GetMapping("/{id}")
    public ResponseEntity<Book> singleBook(@PathVariable(value = "id") long id) {
        Optional<Book> bookO = Optional.ofNullable(bookRepository.findById(id));
        if (!bookO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            bookO.get().add(linkTo(methodOn(BookController.class).listAll()).withRel("List of books"));
            return new ResponseEntity<Book>(bookO.get(), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save book")
    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody @Validated Book book) {
        return new ResponseEntity<Book>(bookRepository.save(book), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a book")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") long id){
        Optional<Book> bookO = Optional.ofNullable(bookRepository.findById(id));
        if (!bookO.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            bookRepository.delete(bookO.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update book")
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value="id") long id,
                                                 @RequestBody @Validated Book book) {
        Optional<Book> bookO = Optional.ofNullable(bookRepository.findById(id));
        if(!bookO.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        book.setId(bookO.get().getId());
        return new ResponseEntity<Book>(bookRepository.save(book), HttpStatus.OK);
    }

    @ApiOperation(value = "Return a list of books order by name")
    @GetMapping(value = "/sortByName")
    public ResponseEntity<List<Book>> findOrderByName() {
        List<Book> bookList = bookRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        if (bookList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Book book : bookList) {
                long id = book.getId();
                book.add(linkTo(methodOn(BookController.class).singleBook(id)).withSelfRel());
            }
            return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Return a list of books order by author and date")
    @GetMapping(value = "/sortByAuthorAndDate")
    public ResponseEntity<List<Book>> findOrderByAuthorAndDate() {
        Sort sort = Sort.by(Sort.Order.asc("author"), Sort.Order.desc("publicationDate"));
        List<Book> bookList = bookRepository.findAll(sort);
        if (bookList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Book book : bookList) {
                long id = book.getId();
                book.add(linkTo(methodOn(BookController.class).singleBook(id)).withSelfRel());
            }
            return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
        }
    }

}
