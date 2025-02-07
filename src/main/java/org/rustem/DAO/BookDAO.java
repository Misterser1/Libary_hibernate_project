package org.rustem.DAO;

import org.rustem.models.Book;
import org.rustem.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> bookList(){
       return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book bookPage(int book_id){
        return jdbcTemplate.query("SELECT book.*, Person.name AS person_name FROM book LEFT JOIN Person ON Person.person_id = book.person_id WHERE book_id = ?", new Object[]{book_id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void bookAdd(Book book){
        jdbcTemplate.update("INSERT INTO book(book_name, author, year) VALUES(?, ?, ?)",
                book.getBook_name(), book.getAuthor(), book.getYear());
    }

    public void bookUpdate(int book_id, Book bookUpdated){
        jdbcTemplate.update("UPDATE Book SET book_name = ?, author = ?, year = ? WHERE book_id = ?",
                bookUpdated.getBook_name(), bookUpdated.getAuthor(), bookUpdated.getYear(), book_id);
    }

    public void personBookUpdate(int book_id,int person_id){
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE book_id = ?", person_id, book_id);
    }

    public void personBookDeleteUpdate(int book_id){
        jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE book_id = ?", book_id);
    }

    public void bookDelete(int book_id){
        jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?", book_id);
    }

    public List<Book> getBooksPeople(int person_id) {
        return jdbcTemplate.query(
                "SELECT * FROM Book JOIN Person ON Book.person_id = Person.person_id WHERE Person.person_id = ?", new Object[]{person_id},
                new BeanPropertyRowMapper<>(Book.class)
        );
    }
}
