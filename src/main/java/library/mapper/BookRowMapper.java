package library.mapper;

import library.dto.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by pinq on 14.09.17.
 */
@Component
public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        String  isbn = resultSet.getString("ISBN");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String year = resultSet.getString("year");
        String category = resultSet.getString("category");
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYear(year);
        book.setCategory(category);
        return book;
    }
}
