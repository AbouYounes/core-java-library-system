package service;

import model.Book;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import exception.LibraryException;



/**
 * Manages books in the library.
 */
public class LibraryService {

    private final Map<String, Book> books = new HashMap<>();

    /**
     * Adds a book to the library.
     * Or show exception if the book already exist.
     * @param book the book to add
     *
     */
    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new LibraryException(
                    "Book with ISBN " + book.getIsbn() + " already exists"
            );        }
        books.put(book.getIsbn(), book);
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

    /**
     * @return all books sorted by title
     */
    public List<Book> getBooksSortedByTitle() {
        List<Book> result = new ArrayList<>(books.values());
        result.sort(Comparator.comparing(Book::getTitle));
        return result;
    }

    /**
     * @return all books sorted by author
     */
    public List<Book> getBooksSortedByAuthor() {
        List<Book> result = new ArrayList<>(books.values());
        result.sort(Comparator.comparing(Book::getAuthor));
        return result;
    }

    /**
     * @return only available books
     */
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Show exception if the book doest exist, or already borrowed.
     * @param isbn the book to borrow
     *
     */
    public void borrowBook(String isbn) {
        Book book = books.get(isbn);

        if (book == null) {
            throw new LibraryException("Book with ISBN " + isbn + " not found");
        }

        if (!book.isAvailable()) {
            throw new LibraryException("Book is already borrowed");
        }

        book.borrow();
    }


}
