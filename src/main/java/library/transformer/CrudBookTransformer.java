package library.transformer;

import org.springframework.stereotype.Component;

import library.dto.Book;
import library.entity.CrudBookEntity;

@Component
public class CrudBookTransformer {

    public CrudBookEntity transformToBookEntity(Book book) {
        CrudBookEntity bookEntity = new CrudBookEntity();
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setCategory(book.getCategory());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setTitle(book.getTitle());
        return bookEntity;
    }

    public Book transformToBook(CrudBookEntity bookEntity) {
        Book book = new Book();
        book.setAuthor(bookEntity.getAuthor());
        book.setCategory(bookEntity.getCategory());
        book.setIsbn(bookEntity.getIsbn());
        book.setTitle(bookEntity.getTitle());
        return book;
    }
}
