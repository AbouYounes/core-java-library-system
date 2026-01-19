package service;

import exception.LibraryException;
import model.Book;

import java.util.*;

/**
 * In-memory implementation of LibraryService.
 *
 * Responsibilities:
 * - Enforce business rules
 * - Manage books in memory
 *
 * Does NOT:
 * - Handle files
 * - Handle UI
 */
public class LibraryServiceImpl implements LibraryService {

    /** Internal storage of books (key = ISBN) */
    private final Map<String, Book> booksByIsbn = new HashMap<>();

    @Override
    public void addBook(Book book) {
        if (booksByIsbn .containsKey(book.getIsbn())) {
            throw new LibraryException(
                    "Book with ISBN " + book.getIsbn() + " already exists"
            );        }
        booksByIsbn.put(book.getIsbn(), book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return booksByIsbn.get(isbn);
    }

    @Override
    public void borrowBook(String isbn) {
        Book book = booksByIsbn.get(isbn);

        if (book == null) {
            throw new LibraryException("Book with ISBN " + isbn + " not found");
        }

        if (!book.isAvailable()) {
            throw new LibraryException("Book with ISBN " + isbn + " is already borrowed");
        }

        book.borrow();
    }

    @Override
    public Collection<Book> getAllBooks() {
        return new ArrayList<>(booksByIsbn.values());
    }

    @Override
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : booksByIsbn.values()) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> getBooksSortedByTitle() {
        List<Book> result = new ArrayList<>(booksByIsbn.values());
        result.sort(Comparator.comparing(Book::getTitle));
        return result;
    }

    @Override
    public List<Book> getBooksSortedByAuthor() {
        List<Book> result = new ArrayList<>(booksByIsbn.values());
        result.sort(Comparator.comparing(Book::getAuthor));
        return result;
    }






}
