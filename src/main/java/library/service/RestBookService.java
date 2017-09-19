package library.service;

import library.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import library.dto.Book;


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

    public boolean update(Book book, String isbn) {
        bookDao.updateBookInAvailableBooks(book,isbn);
       return bookDao.updateBookInAllBooks(book, isbn);
    }

    public boolean exist(String isbn) {
        return bookDao.exist(isbn);
    }
}
