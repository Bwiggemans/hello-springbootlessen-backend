package nl.novi.hello.service;

import nl.novi.hello.dto.BookRequestDto;
import nl.novi.hello.exception.BadRequestException;
import nl.novi.hello.exception.RecordNotFoundException;
import nl.novi.hello.model.Book;
import nl.novi.hello.model.Person;
import nl.novi.hello.repository.BookRepository;
import nl.novi.hello.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Iterable<Person> getPersons(String title){
            return personRepository.findAll();
    }

    public Person getPerson(int id){
        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()){
            return optionalPerson.get();
        }
        else{
            //exception maken
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void deletePerson(int id){
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public int addPerson(Person person) throws BadRequestException {
        Person newPerson = personRepository.save(person);
        return newPerson.getId();
    }

}
