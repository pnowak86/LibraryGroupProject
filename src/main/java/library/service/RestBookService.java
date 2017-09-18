package library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import library.dao.BookDao;
import library.dao.CrudBookRepository;
import library.dao.JdbcBookDao;
import library.entity.CrudBookEntity;
import library.transformer.CrudBookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import library.dto.Book;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


@Service
public class RestBookService {

    private BookDao bookDao;

    @Autowired
    public RestBookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public String create(Book book) {
        bookDao.createForAvailable(book);
        bookDao.createForAllBooks(book);
        return book.getIsbn();
    }

    public Iterable<Book> getAll() {
        return bookDao.getAll();
    }

    public boolean delete(String isbn) {
        bookDao.removeBookFromAvailable(isbn);
        bookDao.removeBookFromAllBooks(isbn);
        return true;
    }

    public Book get(String isbn) {
        return bookDao.getBookFromAvailable(isbn);
    }

    public boolean update(Book book) {
       return bookDao.updateBook(book);
    }
}
