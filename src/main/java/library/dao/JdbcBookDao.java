package library.dao;

import library.dto.Book;
import library.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by RENT on 2017-09-11.
 */
@Repository
public class JdbcBookDao implements BookDao {
    private static final String SELECT_ALL = "SELECT * FROM all_books;";
    


    private JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean create(Book book) {
        return false;
    }

    @Override
    public List<Book> getAll() {
        List<Book> allBooks = jdbcTemplate.query(SELECT_ALL, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet resultSet, int i) throws SQLException {
                String  isbn = resultSet.getString("ISBN");
                String title = resultSet.getNString("title");
                String author = resultSet.getNString("author");
                String year = resultSet.getNString("year");
                String category = resultSet.getNString("category");
                Book book = new Book();
                book.setIsbn(isbn);
                book.setTitle(title);
                book.setAuthor(author);
                book.setYear(year);
                book.setCategory(category);


                return book;
            }
        });

        return allBooks;
    }

}
