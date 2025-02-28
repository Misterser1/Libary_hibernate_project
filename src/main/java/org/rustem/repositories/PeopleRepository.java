package org.rustem.repositories;

import org.rustem.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.books")
    List<Person> getPeopleBooks();

    Optional<Person> findByName(String name);
    // Optional<Person> peoplePage(String name);
}
