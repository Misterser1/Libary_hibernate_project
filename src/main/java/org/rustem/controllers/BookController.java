package org.rustem.controllers;

import jakarta.validation.Valid;
import org.rustem.models.Book;
import org.rustem.models.Person;
import org.rustem.services.BookService;
import org.rustem.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String bookList(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size, @RequestParam(name = "sort", required = false) boolean sort){
        Sort sortConvert = Sort.by(sort ? Sort.Direction.ASC : Sort.Direction.DESC, "year");
        model.addAttribute("books", bookService.findAll(page, size, sortConvert));
        return "book/list";
    }

    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("people", peopleService.findPeopleBook());
        return "book/book_page";
    }

    @GetMapping("/add")
    public String bookAdd(@ModelAttribute("book") Book book){
        return "book/add";
    }

    @GetMapping("/search")
    public String bookSearch(@ModelAttribute("book") Book book){
        return "book/search";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute("book") Book book, @RequestParam(value = "search") String bookName,
                         Model model){
        List<Book> foundBooks = bookService.search(bookName);
        if(foundBooks.isEmpty()){
            model.addAttribute("error", "Книга не найдена");
        }else{
            model.addAttribute("books", foundBooks);
        }

        return "book/search";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/add";
        }

        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String bookEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findById(id));
        return "book/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "book/edit";
        }

        bookService.update(id, book);
        return "redirect:/book";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int book_id,
                         @RequestParam("person_id") int person_id) {
        bookService.personBookUpdate(book_id, person_id);
        return "redirect:/book/" + book_id;
    }

    @PatchMapping("/{id}/delete")
    public String updateDelete(@PathVariable("id") int id) {
        bookService.personBookDeleteUpdate(id);
        return "redirect:/book/" + id;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/book";
    }

}
