package org.rustem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть в диапозоне от 2 до 30 символов")
    @Column(name = "name")
    @Pattern(regexp = "[А-Яа-я]*?\\s[А-Яа-я]*?\\s[А-Яа-я]*", message = "Нужно ввести данные в формате: ФИО")
    private String name;

    @Min(value = 1000, message = "Год рождения должен содержать хотя бы 4 цифры")
    @Range(min = 1000, max = 9999, message = "Год рождения должен состоять из 4 цифр")
    //@Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$", message = "Нужно ввести данные в формате: dd-mm-yyyy")
    @Column(name = "date_of_birthday")
    private int dateOfBirth;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public Person(){

    }

    public Person(int id, String name, int dateOfBirth){
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }


    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", books=" + books +
                '}';
    }

    //    public String getBook_name() {
//        return book_name;
//    }
//
//    public void setBook_name(String book_name) {
//        this.book_name = book_name;
//    }
}
