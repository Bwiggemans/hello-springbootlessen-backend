package nl.novi.hello.service;

import nl.novi.hello.dto.BookRequestDto;
import nl.novi.hello.exception.BadRequestException;
import nl.novi.hello.exception.RecordNotFoundException;
import nl.novi.hello.model.Book;
import nl.novi.hello.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> getBooks(String title){
        if (title.isEmpty()){
            return bookRepository.findAll();
        }else{
            return bookRepository.findAllByTitleContainingIgnoreCase(title);
        }
    }

    public Book getBook(int id){
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()){
            return optionalBook.get();
        }
        else{
            //exception maken
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void deleteBook(int id){
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public int addBook(BookRequestDto bookRequestDto){


        String isbn = bookRequestDto.getIsbn();
        List<Book> books = (List<Book>)bookRepository.findAllByIsbn(isbn);
        if (books.size() > 0) {
            throw new BadRequestException("Isbn already exists!!!");
        }

        Book book = new Book();
        book.setAuthor(bookRequestDto.getAuthor());
        book.setTitle(bookRequestDto.getTitle());
        book.setIsbn(bookRequestDto.getIsbn());

        Book newBook = bookRepository.save(book);
        return newBook.getId();
    }

    public void updateBook(int id, Book book){
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (!book.getTitle().isEmpty()){
            existingBook.setTitle(book.getTitle());
        }
        if (!book.getAuthor().isEmpty()){
            existingBook.setAuthor(book.getAuthor());
        }
        if (!book.getIsbn().isEmpty()){
            existingBook.setIsbn(book.getIsbn());
        }
        bookRepository.save(existingBook);
    }

    public void partialUpdateBook(int id, Book book){
        Book existingBook = bookRepository.findById(id).orElse(null);

        if (!(book.getTitle()==null) && !book.getTitle().isEmpty()){
            existingBook.setTitle(book.getTitle());
        }
        if (!(book.getAuthor()==null) && !book.getAuthor().isEmpty()){
            existingBook.setAuthor(book.getAuthor());
        }
        if (!(book.getIsbn()==null) && !book.getIsbn().isEmpty()){
            existingBook.setIsbn(book.getIsbn());
        }
        bookRepository.save(existingBook);
    }
}
