package library.controller;

import library.dto.Book;
import library.dto.CrudBookResponse;
import library.service.RestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by pinq on 15.09.17.
 */
@RestController
public class RestBookController {

    private RestBookService crudBookService;

    @Autowired
    public RestBookController(RestBookService crudBookService) {
        this.crudBookService = crudBookService;
    }

    @RequestMapping(value = "/v1/library/book", method = RequestMethod.POST)
    public ResponseEntity<CrudBookResponse> createBook(@RequestBody Book book) {
        String isbn = crudBookService.create(book);
        return new ResponseEntity<>(new CrudBookResponse(String.format("Book with id: %s has been created", isbn)),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/v1/library/book/{isbn}",method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody Book book, @PathVariable ("isbn") String isbn) {
        if(!crudBookService.exist(isbn)){
            return new ResponseEntity<>("Provided isbn does not exist!",HttpStatus.BAD_REQUEST);
        }
        boolean updated = crudBookService.update(book, isbn);
        return updated ? new ResponseEntity<>(new CrudBookResponse("Successfully updated"), HttpStatus.OK)
                : createNonExisting(book.getIsbn());
    }

    @RequestMapping(value = "/v1/library/book/all", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Book>> getAll() {
        Iterable<Book> allBook = crudBookService.getAll();
        return new ResponseEntity<>(allBook, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/library/book/{isbn}", method = RequestMethod.GET)
    public ResponseEntity getBook(@PathVariable("isbn") String isbn) {
        Book book = crudBookService.get(isbn);
        return Objects.isNull(book) ? createNonExisting(isbn) : new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/library/book/{isbn}", method = RequestMethod.DELETE)
    public ResponseEntity<CrudBookResponse> delete(@PathVariable("isbn") String isbn) {
        boolean deleted = crudBookService.delete(isbn);
        return deleted ? new ResponseEntity<>(new CrudBookResponse("Successfully deleted"), HttpStatus.OK)
                : createNonExisting(isbn);
    }

    private ResponseEntity<CrudBookResponse> createNonExisting(String isbn) {
        return new ResponseEntity<>(new CrudBookResponse(String.format("Id: %s does not exist", isbn)),
                HttpStatus.BAD_REQUEST);
    }




}
