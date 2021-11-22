package nl.novi.hello.controller;

import nl.novi.hello.model.Book;
import nl.novi.hello.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class BookController {

    //Attributen
    private List<Book> books = new ArrayList<>();

    //Constructor
    public BookController() {
        Book boek1 = new Book();
        boek1.setTitle("Harry Potter");
        boek1.setAuthor("Rowling");
        boek1.setIsbn("2355487756654844");
        books.add(boek1);

        Book boek2 = new Book();
        boek2.setTitle("Harry Potter, deel 2");
        boek2.setAuthor("Rowling");
        boek2.setIsbn("2355487756658214");
        books.add(boek2);
    }
    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/books")
    public ResponseEntity<Object> getBooks(){
        return ResponseEntity.ok(bookRepository.findAll()); //Jackson zit hiertussen vertaling object => JSON
    }
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Object> getBook(@PathVariable int id){
        return ResponseEntity.ok(bookRepository.findAllById(id)); //Jackson zit hiertussen vertaling object => JSON
    }
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {
        //books.remove(id); deze regel gebruiken in geval => private List<Book> books = new ArrayList<>();
        bookRepository.deleteById(id); // Postmen code verwijderen uit postgreSQL
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/books")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        Book newBook = bookRepository.save(book);
        int newId = newBook.getId();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }
    @PatchMapping(value = "/books/{id}")
    public ResponseEntity<Object> partialBook(@PathVariable int id, @RequestBody Book book) {
        Book existingBook = books.get(id);
        if (!book.getTitle().isEmpty()){
            existingBook.setTitle(book.getTitle());
        }
        if (!book.getAuthor().isEmpty()){
            existingBook.setAuthor(book.getAuthor());
        }
        if (!book.getIsbn().isEmpty()){
            existingBook.setIsbn(book.getIsbn());
        }
        books.set(id, existingBook);
        return ResponseEntity.noContent().build();
    }
}
