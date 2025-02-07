package org.rustem.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class Book {
    private int book_id;

    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(max = 100)
    private String book_name;

    @NotEmpty(message = "Автор не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть в диапозоне от 2 до 30 символов")
    private String author;

    @Min(value = 1000, message = "Год должен содержать хотя бы 4 цифры")
    @Range(min = 1000, max = 9999, message = "Год должен состоять из 4 цифр")
    private int year;

    private Integer person_id;

    private String person_name;

    public Book(){

    }

    public Book(int book_id, String book_name, String author, int year, Integer person_id, String person_name) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.author = author;
        this.year = year;
        this.person_id = person_id;
        this.person_name =  person_name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }
}
