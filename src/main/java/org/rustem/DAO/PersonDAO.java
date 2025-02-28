//package org.rustem.DAO;
//
//import org.rustem.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PersonDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> peopleList(){
//        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
////    public  Person peoplePage(int person_id){
////        return jdbcTemplate.query("SELECT * FROM person WHERE person_id = ?", new Object[]{person_id},new BeanPropertyRowMapper<>(Person.class))
////                .stream().findAny().orElse(null);
////    }
//
//    public  Person peoplePage(int person_id){
//        return jdbcTemplate.query("SELECT Person.*, Book.book_name as book_name FROM person left join Book on person.person_id = Book.person_id WHERE Person.person_id = ?", new Object[]{person_id},new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public Optional<Person> peoplePage(String name){
//        return jdbcTemplate.query("SELECT * FROM person WHERE name = ?", new Object[]{name},new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//    }
//
//    public void personAdd(Person person){
//        jdbcTemplate.update("INSERT INTO person(name, date_of_birthday) VALUES (?, ?)", person.getName(), person.getDate_of_birthday());
//    }
//
//    public void personUpdate(int person_id, Person updatedPerson){
//        jdbcTemplate.update("UPDATE Person SET name = ?, date_of_birthday = ? WHERE person_id = ?", updatedPerson.getName(), updatedPerson.getDate_of_birthday(), person_id);
//    }
//
//    public void personDelete(int person_id){
//        jdbcTemplate.update("DELETE FROM Person WHERE person_id = ?", person_id);
//    }
//
//    public List<Person> getPeopleBooks() {
//        return jdbcTemplate.query(
//                "SELECT * FROM Person",
//                new BeanPropertyRowMapper<>(Person.class)
//        );
//    }
//
//}
