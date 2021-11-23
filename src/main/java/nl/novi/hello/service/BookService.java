package nl.novi.hello.service;

import nl.novi.hello.model.Book;
import nl.novi.hello.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book getBook(int id){
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }

    public int addBook(Book book){
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
