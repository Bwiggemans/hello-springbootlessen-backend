package nl.novi.hello.repository;

import nl.novi.hello.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {


    Object findAllById(int id);
}
