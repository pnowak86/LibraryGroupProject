package library.dao;

import library.dto.Book;
import library.dto.User;
import library.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BookDao {
    boolean create(Book book);

    List<Book> getAll();
    List<Book> getUserRented();
}
