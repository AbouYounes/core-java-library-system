package model;
import java.time.LocalDateTime;
import java.time.Duration;



/**
 * Represents a book in the library.
 * A book is uniquely identified by its ISBN.
 */
public class Book {

    private final String isbn;
    private final String title;
    private final String author;
    private boolean available;
    private final LocalDateTime createdAt;
    private LocalDateTime borrowedAt;


    public Book(String isbn, String title, String author) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
        this.createdAt = LocalDateTime.now();

    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }


    /**
     * Marks the book as borrowed.
     * Stamp a borrow datetime.
     */
    public void borrow() {
        if (!available) {
            throw new IllegalStateException("Book is already borrowed");
        }
        this.available = false;
        this.borrowedAt = LocalDateTime.now();
    }

    public long getBorrowedDurationInHours() {
        if (borrowedAt == null) {
            return 0;
        }
        return Duration.between(borrowedAt, LocalDateTime.now()).toHours();
    }



    /**
     * Marks the book as returned.
     * Stamp a returnBook datetime.
     */
    public void returnBook() {
        this.available = true;
        this.borrowedAt = null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

}
