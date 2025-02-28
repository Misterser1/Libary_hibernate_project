package org.rustem.controllers;

import jakarta.validation.Valid;
import org.rustem.models.Person;
import org.rustem.services.BookService;
import org.rustem.services.PeopleService;
//import org.rustem.util.PeopleValidator;
import org.rustem.util.PeopleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final BookService bookService;
    private final PeopleValidator peopleValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, BookService bookService, PeopleValidator peopleValidator) {
        this.peopleService = peopleService;
        this.bookService = bookService;
        this.peopleValidator = peopleValidator;
    }

    @GetMapping()
    public String peopleList(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "people/list";
    }

    @GetMapping("/{id}")
    public String peoplePage(@PathVariable("id") int id, Model model){
        model.addAttribute("people", peopleService.findById(id));
        model.addAttribute("books", bookService.findBookPeople(id));
        return "people/person";
    }

    @GetMapping("/add")
    public String peopleAdd(@ModelAttribute("person") Person person){
        return "people/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        peopleValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "people/add";
        }

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String personEdit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", peopleService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id){
        peopleValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "people/edit";
        }

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
