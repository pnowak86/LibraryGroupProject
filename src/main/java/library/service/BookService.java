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
    private BookDao bookRepository;
    private BookTransformer bookTransformer;
    private BookDao bookDao;
    @Autowired
    public BookService(BookDao bookRepository, BookTransformer bookTransformer) {
        this.bookRepository = bookRepository;
        this.bookTransformer = bookTransformer;
    }

//    public Iterable<Book> getAll() {
//        Iterable<BookEntity> all = bookRepository.findAll();
//        List<Book> result = new ArrayList<>();
//        for (BookEntity bookEntity : all) {
//            result.add(bookTransformer.transformToBook(bookEntity));
//        }
//        return result;
//    }

    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }


}
