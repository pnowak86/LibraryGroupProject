package library.service;

import library.dao.BookDao;
import library.dao.UserDao;
import library.dto.Book;
import library.entity.BookEntity;
import library.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-09-11.
 */
@Service
public class BookService {
    private BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }


    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }


    public void create(Book book) {

        bookDao.create( book);
    }

    public List<Book> getUserRentedBooks() {
        return bookDao.getUserRented();
    }


}