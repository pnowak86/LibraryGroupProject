package library.controller;

import library.config.UserResponse;
import library.dto.Book;
import library.dto.User;
import library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Book> allBooks = bookService.showRented();
        model.addAttribute("rentedbooks", allBooks);
        return "rentedbooks";
    }

    @RequestMapping(value = "/rentedbook/{isbn}", method = RequestMethod.POST)
    public String getUserRentedBooks(@PathVariable("isbn") String isbn, Model model) {
        bookService.rentBook(isbn);
        List<Book> books = bookService.showAvailable();
        model.addAttribute("available", books);
        return "available";
    }

    @RequestMapping(value = "/returnbook/{isbn}", method = RequestMethod.POST)
    public String getUserReturnedBooks(@PathVariable("isbn") String isbn, Model model) {
        bookService.returnBook(isbn);
        List<Book> books = bookService.showRented();
        model.addAttribute("rentedbooks", books);
        return "rentedbooks";
    }

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    public String getAllAvailable(Model model) {
        List<Book> allBooks = bookService.showAvailable();
        model.addAttribute("available", allBooks);
        return "available";
    }
}
