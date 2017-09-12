package library.controller;

import library.dto.Book;
import library.dto.User;
import library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by RENT on 2017-09-11.
 */
@Controller
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getAllBooks(Model model) {
        List<Book> allBooks = bookService.getAllBooks();
        model.addAttribute("books", allBooks);
        return "books";
    }

    @RequestMapping(value = "/rentedbooks", method = RequestMethod.GET)
    public String getUserRentedBooks(Model model) {
        List<Book> allUserRentedBooks = bookService.getUserRentedBooks();
        model.addAttribute("rentedbooks", allUserRentedBooks);
        return "rentedbooks";
    }

}
