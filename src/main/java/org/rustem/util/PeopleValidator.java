package org.rustem.util;

import org.rustem.DAO.PersonDAO;
import org.rustem.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PeopleValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(personDAO.peoplePage(person.getName()).isPresent()){
            errors.rejectValue("name", "", "Это имя уже занято");
        }
    }
}
