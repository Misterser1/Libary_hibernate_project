package org.rustem.services;

import org.hibernate.StaleStateException;
import org.rustem.models.Book;
import org.rustem.models.Person;
import org.rustem.repositories.BooksRepository;
import org.rustem.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;
    private final PeopleService peopleService;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BookService(BooksRepository booksRepository, PeopleService peopleService, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll(int page, int itemsPerPage, Sort sort){
        return booksRepository.findAll(PageRequest.of(page, itemsPerPage, sort)).getContent();
    }

    public Book findById(int id){
        Optional<Book> book =  booksRepository.findById(id);
        return book.orElse(null);
    }

    public List<Book> findBookPeople(int id){
        List<Book> books = booksRepository.getBooksPeople(id);
        for (Book book : books) {
            if (book.getDateTime() != null) {
                LocalDateTime returnDeadline = book.getDateTime().plusDays(10);
                book.setOverdue(LocalDateTime.now().isAfter(returnDeadline));
            } else {
                book.setOverdue(false);
            }
        }
        return books;
    }

    public List<Book> search(String bookName){
        return booksRepository.findByBookNameStartingWith(bookName);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void personBookUpdate(int bookId, int personId) {
        Person person = peopleService.findById(personId);
        Book book = booksRepository.findById(bookId).orElse(null);
        if (book != null) {
            if (book.getDateTime() == null) {
                book.setDateTime(LocalDateTime.now());
                booksRepository.save(book);
            }
        }
        booksRepository.personBookUpdate(bookId, person);
    }


    @Transactional
    public void personBookDeleteUpdate(int id){
        Book book = booksRepository.findById(id).orElse(null);
        if (book != null) {
            if (book.getDateTime() != null) {
                book.setDateTime(null);
                booksRepository.save(book);
            }
        }
        booksRepository.personBookDeleteUpdate(id);
    }
}
