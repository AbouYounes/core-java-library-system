package model;

/**
 * Represents a book in the library.
 * A book is uniquely identified by its ISBN.
 */
public class Book {

    private final String isbn;
    private final String title;
    private final String author;
    private boolean available;

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

    /**
     * Marks the book as borrowed.
     */
    public void borrow() {
        if (!available) {
            throw new IllegalStateException("Book is already borrowed");
        }
        this.available = false;
    }

    /**
     * Marks the book as returned.
     */
    public void returnBook() {
        this.available = true;
    }
}
