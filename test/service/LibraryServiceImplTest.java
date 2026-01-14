package service;

import exception.LibraryException;
import model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for LibraryServiceImpl.
 *
 * Focus:
 * - Business rules
 * - Valid behavior
 * - Error conditions
 */
class LibraryServiceImplTest {

    @Test
    void shouldAddBookSuccessfully() {
        LibraryService library = new LibraryServiceImpl();

        Book book = new Book("123", "Clean Code", "Robert Martin");
        library.addBook(book);

        assertNotNull(library.findBookByIsbn("123"));
    }

    @Test
    void shouldNotAllowDuplicateIsbn() {
        LibraryService library = new LibraryServiceImpl();

        library.addBook(new Book("123", "Clean Code", "Robert Martin"));

        assertThrows(
                LibraryException.class,
                () -> library.addBook(new Book("123", "Another Book", "Someone Else"))
        );
    }

    @Test
    void shouldAllowBorrowingAvailableBook() {
        LibraryService library = new LibraryServiceImpl();
        library.addBook(new Book("123", "Clean Code", "Robert Martin"));

        library.borrowBook("123");

        assertFalse(library.findBookByIsbn("123").isAvailable());
    }

    @Test
    void shouldNotAllowBorrowingAlreadyBorrowedBook() {
        LibraryService library = new LibraryServiceImpl();
        library.addBook(new Book("123", "Clean Code", "Robert Martin"));

        library.borrowBook("123");

        assertThrows(
                LibraryException.class,
                () -> library.borrowBook("123")
        );
    }

    @Test
    void shouldThrowExceptionWhenBookNotFound() {
        LibraryService library = new LibraryServiceImpl();

        assertThrows(
                LibraryException.class,
                () -> library.borrowBook("999")
        );
    }
}
