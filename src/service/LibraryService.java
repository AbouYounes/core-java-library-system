package service;

import model.Book;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages books in the library.
 */
public class LibraryService {

    private final Map<String, Book> books = new HashMap<>();

    /**
     * Adds a book to the library.
     *
     * @param book the book to add
     * @return true if added, false if already exists
     */
    public boolean addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            return false;
        }
        books.put(book.getIsbn(), book);
        return true;
    }

    /**
     * Finds a book by ISBN.
     *
     * @param isbn the ISBN to search for
     * @return the book, or null if not found
     */
    public Book findBookByIsbn(String isbn) {
        return books.get(isbn);
    }

    /**
     * @return all books in the library
     */
    public Map<String, Book> getAllBooks() {
        return books;
    }
}
