package nl.novi.hello.repository;

import nl.novi.hello.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Iterable<Book> findAllByTitle(String title);
    Iterable<Book> findAllByTitleContainingIgnoreCase(String title);
    Iterable<Book> findAllByIsbn(String isbn);


    /*  Deze werkt nog niet
    @Query("SELECT * FROM books b WHERE b.title LIKE %:s%")
    Iterable<Book> searchByNameLike(@Param("s") String s);
 */
//  Zoeken met gedeelte String in attribuut title:
    @Query(value = "SELECT * FROM books b WHERE b.title LIKE %:s%", nativeQuery = true) // using SQL
    Iterable<Book> searchByTitleLike(@Param("s") String s);

}
