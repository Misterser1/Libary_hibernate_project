package org.rustem.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private int person_id;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть в диапозоне от 2 до 30 символов")
    private String name;

    @Min(value = 1000, message = "Год рождения должен содержать хотя бы 4 цифры")
    @Range(min = 1000, max = 9999, message = "Год рождения должен состоять из 4 цифр")
    private int date_of_birthday;

    private String book_name;

    public Person(){

    }

    public Person(int person_id, String name, int date_of_birthday, String book_name){
        this.person_id = person_id;
        this.name = name;
        this.date_of_birthday = date_of_birthday;
        this.book_name = book_name;
    }


    public int getDate_of_birthday() {
        return date_of_birthday;
    }

    public void setDate_of_birthday(int date_of_birthday) {
        this.date_of_birthday = date_of_birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
}
