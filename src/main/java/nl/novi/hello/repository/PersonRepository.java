package nl.novi.hello.repository;

import nl.novi.hello.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
