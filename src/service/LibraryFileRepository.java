package service;

import model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles file-based persistence of books.
 *
 * Responsibilities:
 * - Save books to file
 * - Load books from file
 *
 * Does NOT:
 * - Apply business rules
 * - Know about services
 */
public class LibraryFileRepository {

    private final File file;

    public LibraryFileRepository(String filename) {
        this.file = new File(filename);
    }

    /**
     * Saves all books to the file.
     *
     * @param books books to save
     */
    public void save(Iterable<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Book book : books) {
                writer.println(serialize(book));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save library data", e);
        }
    }

    /**
     * Loads books from the file.
     *
     * @return list of loaded books
     */
    public List<Book> load() {
        List<Book> books = new ArrayList<>();

        if (!file.exists()) {
            return books;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(deserialize(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load library data", e);
        }

        return books;
    }

    // ===== Serialization helpers =====

    private String serialize(Book book) {
        return String.join(",",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                String.valueOf(book.isAvailable())
        );
    }

    private Book deserialize(String line) {
        String[] parts = line.split(",");
        Book book = new Book(parts[0], parts[1], parts[2]);

        if (!Boolean.parseBoolean(parts[3])) {
            book.borrow();
        }
        return book;
    }
}
