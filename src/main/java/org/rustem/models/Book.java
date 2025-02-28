package org.rustem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(max = 100)
    @Column(name = "name")
    private String bookName;

    @NotEmpty(message = "Автор не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть в диапозоне от 2 до 30 символов")
    @Column(name = "author")
    private String author;

    @Min(value = 1000, message = "Год должен содержать хотя бы 4 цифры")
    @Range(min = 1000, max = 9999, message = "Год должен состоять из 4 цифр")
    @NotNull(message = "Год издания не может быть пустым")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "time")
    LocalDateTime dateTime;

    @Transient
    private boolean overdue;

//    private String person_name;

    public Book(){

    }

    public Book(int id, String bookName, String author, int year) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public boolean getOverdue(){
        return overdue;
    }

    public void setOverdue(boolean overdue){
        this.overdue = overdue;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", owner=" + owner +
                '}';
    }

    //    public String getPerson_name() {
//        return person_name;
//    }
//
//    public void setPerson_name(String person_name) {
//        this.person_name = person_name;
//    }

}
