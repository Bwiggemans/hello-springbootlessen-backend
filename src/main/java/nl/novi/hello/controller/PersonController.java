package nl.novi.hello.controller;

import nl.novi.hello.exception.BadRequestException;
import nl.novi.hello.model.Book;
import nl.novi.hello.model.Person;
import nl.novi.hello.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/persons")
    public ResponseEntity<Object> getPersons(@RequestParam(name="title", defaultValue = "")String title){
        return ResponseEntity.ok(personService.getPersons(title)); //Jackson zit hiertussen vertaling object => JSON
    }
    @GetMapping(value = "/persons/{id}")
    public ResponseEntity<Object> getPerson(@PathVariable int id){
        return ResponseEntity.ok(personService.getPerson(id)); //Jackson zit hiertussen vertaling object => JSON
    }
    @DeleteMapping(value = "/persons/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable int id) {
        //persons.remove(id); deze regel gebruiken in geval => private List<Person> persons = new ArrayList<>();
        personService.deletePerson(id); // Postmen code verwijderen uit postgreSQL
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/persons")
    public ResponseEntity<Object> addPerson(@RequestBody Person person) throws BadRequestException {
        int newId = personService.addPerson(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping(value = "/persons/{id}/books")
    public ResponseEntity<Object> getPersonBooks(@PathVariable int id){
        return ResponseEntity.ok(personService.getPersonBooks(id)); //Jackson zit hiertussen vertaling object => JSON
    }
    @PostMapping(value = "/persons/{id}/books")
    public ResponseEntity<Object> addPersonBook(@PathVariable int id, @RequestBody Book book){
        personService.addPersonBook(id, book);
        return ResponseEntity.created(null).build(); //Jackson zit hiertussen vertaling object => JSON
    }
}
