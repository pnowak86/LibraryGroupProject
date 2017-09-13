package library.dao;

import library.dto.Book;
import library.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private static final String SELECT_ALL_RENTED = "SELECT * FROM rented_books where username = ";
    private  static final String ADD_BOOK_TO_RENTED = "INSERT INTO rented_books(ISBN, title, author, year, username, category) VALUES(?, ?, ?, ?,?,?)";

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean create(Book book) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        jdbcTemplate.update("INSERT INTO rented_books(ISBN, title, author, year, username, category) VALUES(?, ?, ?, ? ,? ,?)", "12345", "testowajava", "testowy autor", "testowty rok", "admin90", "drama" );


        return true;


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





    @Override
    public List<Book> getUserRented() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<Book> rentedBooks = jdbcTemplate.query(SELECT_ALL_RENTED + "\'" +name+"\'" , new RowMapper<Book>() {

            @Override
            public Book mapRow(ResultSet resultSet, int i) throws SQLException {
                String isbn = resultSet.getString("ISBN");
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

        return rentedBooks;

    }




}
