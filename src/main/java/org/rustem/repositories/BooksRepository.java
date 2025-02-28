package org.rustem.repositories;

import org.rustem.models.Book;
import org.rustem.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.owner.id = :id")
    List<Book> getBooksPeople(@Param("id") int id);

    @Modifying
    @Query("UPDATE Book b SET b.owner = :person WHERE b.id = :bookId")
    void personBookUpdate(@Param("bookId") int bookId, @Param("person") Person person);

    @Modifying
    @Query("UPDATE Book b SET b.owner = null WHERE b.id = :bookId")
    void personBookDeleteUpdate(@Param("bookId") int bookId);

//    @Modifying
//    @Query("SELECT b FROM Book b WHERE b.bookName LIKE :search")
//    List<String> searchBookLike(@RequestParam("search") String search);
    List<Book> findByBookNameStartingWith(String bookName);
}
