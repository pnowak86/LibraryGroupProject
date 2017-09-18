package library.dao;

import library.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by RENT on 2017-09-11.
 */
@Repository
public class JdbcBookDao implements BookDao {
    private static final String SELECT_ALL_BOOKS_IN_LIBRARY = "SELECT * FROM all_books;";
    private static final String SELECT_ALL_AVAILABLE = "SELECT * FROM available_books;";
    private static final String SELECT_ALL_RENTED = "SELECT * FROM rented_books where username = ? ";
    private static final String SELECT_ONE_BOOK_FROM_AVAILABLE = "SELECT * FROM available_books where isbn = ?";
    private static final String SELECT_ONE_BOOK_FROM_RENTED= "SELECT * FROM rented_books where isbn = ?";
    public static final String DELETE_FROM_AVAILABLE_BOOKS_WHERE_ISBN = "Delete from available_books where isbn = ?";
    public static final String DELETE_FROM_ALL_BOOKS_WHERE_ISBN = "Delete from all_books where isbn = ?";
    public static final String DELETE_FROM_RENTED_BOOKS_WHERE_ISBN = "Delete from rented_books where isbn = ?";
    public static final String INSERT_INTO_RENTED_BOOKS = "INSERT INTO rented_books(ISBN, title, author, year, username, category) VALUES(?, ?, ?, ? ,? ,?)";
    public static final String INSERT_INTO_AVAILABLE_BOOKS = "INSERT INTO available_books(ISBN, title, author, year, category) VALUES(?, ?, ?, ? ,?)";
    public static final String INSERT_INTO_ALL_BOOKS= "INSERT INTO all_books(ISBN, title, author, year, category) VALUES(?, ?, ?, ? ,?)";
    public static final String UPDATE_BOOK_WHERE_ISBN ="UPDATE all_books  SET title = ?, author= ?, year = ?, category = ? WHERE ISBN = ?;";


    private JdbcTemplate jdbcTemplate;
    private RowMapper<Book> rowMapper;

    @Autowired
    public JdbcBookDao(JdbcTemplate jdbcTemplate,RowMapper<Book> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper =rowMapper;
    }
    @Override
    public boolean updateBook(Book book) {
        jdbcTemplate.update(UPDATE_BOOK_WHERE_ISBN, book.getTitle(),book.getAuthor(),book.getYear(),book.getCategory(), "3435242385711");
        return true;
    }

    @Override
    public boolean createForRented(Book book) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        jdbcTemplate.update(INSERT_INTO_RENTED_BOOKS, book.getIsbn(), book.getTitle(),book.getAuthor(),book.getYear(),name, book.getCategory());
        return true;
    }

    @Override
    public boolean createForAllBooks(Book book) {
        jdbcTemplate.update(INSERT_INTO_ALL_BOOKS, book.getIsbn(), book.getTitle(),book.getAuthor(),book.getYear(), book.getCategory());
        return true;
    }


    @Override
    public boolean createForAvailable(Book book) {
        jdbcTemplate.update(INSERT_INTO_AVAILABLE_BOOKS, book.getIsbn(), book.getTitle(),book.getAuthor(),book.getYear(), book.getCategory());
        return true;
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query(SELECT_ALL_BOOKS_IN_LIBRARY,rowMapper);
    }


    @Override
    public List<Book> getUserRentedBooks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        List<Book> allBooks = jdbcTemplate.query(SELECT_ALL_RENTED, rowMapper,name);
        return allBooks;
    }

    @Override
    public Book getBookFromAvailable(String isbn) {
        return jdbcTemplate.queryForObject(SELECT_ONE_BOOK_FROM_AVAILABLE,rowMapper,isbn);
    }

    @Override
    public Book getBookFromRented(String isbn) {
        return jdbcTemplate.queryForObject(SELECT_ONE_BOOK_FROM_RENTED,rowMapper,isbn);
    }

    @Override
    public void removeBookFromAvailable(String isbn) {
        jdbcTemplate.update(DELETE_FROM_AVAILABLE_BOOKS_WHERE_ISBN,isbn);
    }

    @Override
    public void removeBookFromAllBooks(String isbn) {
        jdbcTemplate.update(DELETE_FROM_ALL_BOOKS_WHERE_ISBN,isbn);
    }


    @Override
    public void removeBookFromRented(String isbn) {
        jdbcTemplate.update(DELETE_FROM_RENTED_BOOKS_WHERE_ISBN,isbn);
    }


    @Override
    public List<Book> getAllAvaiable() {
        List<Book> allBooks = jdbcTemplate.query(SELECT_ALL_AVAILABLE, rowMapper);
        return allBooks;
    }

}
