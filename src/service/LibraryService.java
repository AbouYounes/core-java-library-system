package service;

import model.Book;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Defines the contract for library operations.
 *
 * This interface describes WHAT the library can do,
 * not HOW it does it.
 */
public interface LibraryService {

    /**
     * Adds a book to the library.
     *
     * @param book book to add
     */
    void addBook(Book book);

    /**
     * Finds a book by its ISBN.
     *
     * @param isbn book identifier
     * @return the book or null if not found
     */
    Book findBookByIsbn(String isbn);

    /**
     * Borrows a book using its ISBN.
     *
     * @param isbn book identifier
     */
    void borrowBook(String isbn);

    /**
     * @return all books in the library
     */
    Collection<Book> getAllBooks();

    /**
     * @return sort list of books by Title.
     */
    List<Book> getBooksSortedByTitle();

    /**
     * @return sort list of books by Author.
     */
    List<Book> getBooksSortedByAuthor();

    /**
     * @return list of available books only
     */
    List<Book> getAvailableBooks();
}
