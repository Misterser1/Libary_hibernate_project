//package org.rustem.util;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DbCreate {
//    private final JdbcTemplate jdbcTemplate;
//    private final Environment environment;
//
//    @Autowired
//    public DbCreate(JdbcTemplate jdbcTemplate, Environment environment) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.environment = environment;
//    }
//
//    @PostConstruct
//    public void init() {
//        createTables();
//    }
//
//    private void createTables() {
//        String dbName = environment.getProperty("db.url").split("/")[3]; // Получаем имя БД из URL
//        System.out.println("Подключение к базе данных: " + dbName);
//
//        String createPersonTable = """
//            CREATE TABLE IF NOT EXISTS person (
//                person_id SERIAL PRIMARY KEY,
//                name VARCHAR(30) NOT NULL,
//                date_of_birthday INT CHECK (date_of_birthday BETWEEN 1000 AND 9999)
//            )""";
//
//        String createBookTable = """
//            CREATE TABLE IF NOT EXISTS book (
//                book_id SERIAL PRIMARY KEY,
//                book_name VARCHAR(100) NOT NULL,
//                author VARCHAR(30) NOT NULL,
//                year INT CHECK (year BETWEEN 1000 AND 9999),
//                person_id INT,
//                FOREIGN KEY (person_id) REFERENCES person(person_id) ON DELETE SET NULL
//            )""";
//
//        jdbcTemplate.execute(createPersonTable);
//        jdbcTemplate.execute(createBookTable);
//
//        System.out.println("Таблицы успешно созданы (если их не было)");
//    }
//}
