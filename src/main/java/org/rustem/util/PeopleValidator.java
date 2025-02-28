package org.rustem.util;

import org.rustem.models.Person;
import org.rustem.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PeopleValidator implements Validator {
    private final PeopleService peopleService;


    @Autowired
    public PeopleValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Person thisPerson = peopleService.findByName(person.getName());

        if(peopleService.findByName(person.getName()) != null && thisPerson.getId() != person.getId()){
            errors.rejectValue("name", "", "Это имя уже занято");
        }
    }
}
