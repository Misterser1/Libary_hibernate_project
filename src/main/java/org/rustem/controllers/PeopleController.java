package org.rustem.controllers;

import jakarta.validation.Valid;
import org.rustem.DAO.BookDAO;
import org.rustem.DAO.PersonDAO;
import org.rustem.models.Person;
import org.rustem.util.PeopleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final PeopleValidator peopleValidator;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO, PeopleValidator peopleValidator, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.peopleValidator = peopleValidator;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String peopleList(Model model){
        model.addAttribute("people", personDAO.peopleList());
        return "people/list";
    }

    @GetMapping("/{person_id}")
    public String peoplePage(@PathVariable("person_id") int person_id, Model model){
        model.addAttribute("people", personDAO.peoplePage(person_id));
        model.addAttribute("books", bookDAO.getBooksPeople(person_id));
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

        personDAO.personAdd(person);
        return "redirect:/people";
    }

    @GetMapping("/{person_id}/edit")
    public String personEdit(Model model, @PathVariable("person_id") int person_id){
        model.addAttribute("person", personDAO.peoplePage(person_id));
        return "people/edit";
    }

    @PatchMapping("/{person_id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("person_id") int person_id){
        peopleValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "people/edit";
        }

        personDAO.personUpdate(person_id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{person_id}")
    public String delete(@PathVariable("person_id") int person_id){
        personDAO.personDelete(person_id);
        return "redirect:/people";
    }
}
