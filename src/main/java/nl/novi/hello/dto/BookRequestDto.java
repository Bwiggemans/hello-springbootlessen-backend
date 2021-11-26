package nl.novi.hello.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BookRequestDto {

        //Attributen
        @NotEmpty
        @Size(min=1, max=100)
        private String title;

        @NotEmpty
        @Size(min=1, max=100)
        private String author;

        @NotEmpty
        @Size(min=10, max=20)
        private String isbn;

        //Getters and setters
        public String getTitle() {
                return title;
        }
        public void setTitle(String title) {
                this.title = title;
        }
        public String getAuthor() {
                return author;
        }
        public void setAuthor(String author) {
                this.author = author;
        }
        public String getIsbn() {
                return isbn;
        }
        public void setIsbn(String isbn) {
                this.isbn = isbn;
        }
}
