package org.rustem.controllers;

import jakarta.validation.Valid;
import org.rustem.DAO.BookDAO;
import org.rustem.DAO.PersonDAO;
import org.rustem.models.Book;
import org.rustem.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String bookList(Model model){
        model.addAttribute("books", bookDAO.bookList());
        return "book/list";
    }

    @GetMapping("/{book_id}")
    public String bookPage(@PathVariable("book_id") int book_id, Model model){
        model.addAttribute("book", bookDAO.bookPage(book_id));
        model.addAttribute("people", personDAO.getPeopleWithoutBooks());
        return "book/book_page";
    }

    @GetMapping("/add")
    public String bookAdd(@ModelAttribute("book") Book book){
        return "book/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/add";
        }

        bookDAO.bookAdd(book);
        return "redirect:/book";
    }

    @GetMapping("/{book_id}/edit")
    public String bookEdit(Model model, @PathVariable("book_id") int book_id) {
        model.addAttribute("book", bookDAO.bookPage(book_id));
        return "book/edit";
    }

    @PatchMapping("/{book_id}/edit")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("book_id") int book_id){
        if(bindingResult.hasErrors()){
            return "book/edit";
        }

        bookDAO.bookUpdate(book_id, book);
        return "redirect:/book";
    }

    @PatchMapping("/{book_id}")
    public String update(@PathVariable("book_id") int book_id,
                         @RequestParam("person_id") int person_id) {
        bookDAO.personBookUpdate(book_id, person_id);
        return "redirect:/book/" + book_id;
    }

    @PatchMapping("/{book_id}/delete")
    public String updateDelete(@PathVariable("book_id") int book_id) {
        bookDAO.personBookDeleteUpdate(book_id);
        return "redirect:/book/" + book_id;
    }


    @DeleteMapping("/{book_id}")
    public String delete(@PathVariable("book_id") int book_id){
        bookDAO.bookDelete(book_id);
        return "redirect:/book";
    }

}
