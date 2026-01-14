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
    private final Map<String, Book> books = new HashMap<>();

    @Override
    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new LibraryException(
                    "Book with ISBN " + book.getIsbn() + " already exists"
            );        }
        books.put(book.getIsbn(), book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return books.get(isbn);
    }

    @Override
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

    @Override
    public Collection<Book> getAllBooks() {
        return books.values();
    }
    @Override
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> getBooksSortedByTitle() {
        List<Book> result = new ArrayList<>(books.values());
        result.sort(Comparator.comparing(Book::getTitle));
        return result;
    }

    @Override
    public List<Book> getBooksSortedByAuthor() {
        List<Book> result = new ArrayList<>(books.values());
        result.sort(Comparator.comparing(Book::getAuthor));
        return result;
    }






}
