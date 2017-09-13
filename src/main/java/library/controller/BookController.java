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


    @RequestMapping(value = "/booksrented", method = RequestMethod.POST)
    public String register(@ModelAttribute Book book, Model model) {
        bookService.create(book);
        boolean success = true;
        model.addAttribute("success", success);
        model.addAttribute("register", !success);

        return "added to rent";
    }



    @RequestMapping(value = "/rentedbooks/{isbn}", method = RequestMethod.POST)
    public String getUserRentedBooks(@PathVariable("isbn") String isbn,Model model) {
        //TODO: Usun z tabeli available i wrzuc do tabeli rent
       // bookService.rentBook(isbn);
        List<Book> allUserRentedBooks = bookService.getAllBooks();
       allUserRentedBooks= allUserRentedBooks.stream().filter(b->!b.getIsbn().equals(isbn)).collect(Collectors.toList());
        model.addAttribute("books", allUserRentedBooks);
        return "books";
    }

}
