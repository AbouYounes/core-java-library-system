package service;

import model.Book;

import java.util.List;
import java.util.Map;

public interface LibraryService {

    void addBook(Book book);

    Book findBookByIsbn(String isbn);

    void borrowBook(String isbn);

    Map<String, Book> getAllBooks();

    List<Book> getBooksSortedByTitle();

    List<Book> getBooksSortedByAuthor();

    List<Book> getAvailableBooks();
}
