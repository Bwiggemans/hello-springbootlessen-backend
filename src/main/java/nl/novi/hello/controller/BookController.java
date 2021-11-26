package nl.novi.hello.controller;

import nl.novi.hello.exception.BadRequestException;
import nl.novi.hello.model.Book;
import nl.novi.hello.service.BookService;
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

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books")
    public ResponseEntity<Object> getBooks(@RequestParam(name="title", defaultValue = "")String title){
        return ResponseEntity.ok(bookService.getBooks(title)); //Jackson zit hiertussen vertaling object => JSON
    }
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Object> getBook(@PathVariable int id){
        return ResponseEntity.ok(bookService.getBook(id)); //Jackson zit hiertussen vertaling object => JSON
    }
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {
        //books.remove(id); deze regel gebruiken in geval => private List<Book> books = new ArrayList<>();
        bookService.deleteBook(id); // Postmen code verwijderen uit postgreSQL
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/books")
    public ResponseEntity<Object> addBook(@RequestBody Book book) throws BadRequestException {
        int newId = bookService.addBook(book);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping(value = "/books/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book book) {
        bookService.updateBook(id, book);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/books/{id}")
    public ResponseEntity<Object> partialupdateBook(@PathVariable int id, @RequestBody Book book) {
        bookService.partialUpdateBook(id, book);
        return ResponseEntity.noContent().build();
    }
}
