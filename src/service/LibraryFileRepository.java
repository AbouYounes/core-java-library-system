package service;

import model.Book;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Collection;

public class LibraryFileRepository {

    private final File file;

    public LibraryFileRepository(String filename) {
        this.file = new File(filename);
    }

    public void save(Collection<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Book book : books) {
                writer.println(serialize(book));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save library data", e);
        }
    }

    public void load(LibraryServiceImpl libraryService) {
        if (!file.exists()) {
            return; // nothing to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Book book = deserialize(line);
                libraryService.addBook(book);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load library data", e);
        }
    }

    private String serialize(Book book) {
        return String.join(",",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                String.valueOf(book.isAvailable()),
                book.getCreatedAt().toString(),
                book.getBorrowedAt() == null ? "null" : book.getBorrowedAt().toString()
        );
    }

    private Book deserialize(String line) {
        String[] parts = line.split(",", -1);

        Book book = new Book(parts[0], parts[1], parts[2]);

        if (!Boolean.parseBoolean(parts[3])) {
            book.borrow();
        }

        return book;
    }
}
