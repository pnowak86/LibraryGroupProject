package library.service;

import library.dao.BookDao;
import library.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        bookDao.createForRented( book);
    }

    public void rentBook(String isbn){
        Book book = bookDao.getBookFromAvailable(isbn);

                    bookDao.removeBookFromAvailable(isbn);

                    bookDao.createForRented(book);

    }

    public void returnBook(String isbn){
        Book book = bookDao.getBookFromRented(isbn);

        bookDao.removeBookFromRented(isbn);

        bookDao.createForAvailable(book);

    }


    public List<Book> showRented(){
        return bookDao.getUserRentedBooks();
    }

    public List<Book> showAvailable(){
        return bookDao.getAllAvaiable();
    }

//    public List<Book> getUserRentedBooks() {
//        return bookDao.rentBookByUser();
//    }


}