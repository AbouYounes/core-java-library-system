import model.Book;
import service.LibraryFileRepository;
import service.LibraryService;
import service.LibraryServiceImpl;

/**
 * Application entry point.
 *
 * Responsibilities:
 * - Create objects
 * - Wire dependencies
 * - Start the application
 *
 * Does NOT:
 * - Contain business logic
 * - Handle persistence logic
 */
public class Main {

    public static void main(String[] args) {

        // Wiring
        LibraryService library = new LibraryServiceImpl();
        LibraryFileRepository repository = new LibraryFileRepository("library.txt");

        // Load persisted data
        for (Book book : repository.load()) {
            library.addBook(book);
        }

        // Use the application
        library.addBook(new Book("113", "Clean Architecture", "Robert Martin"));
        library.addBook(new Book("114", "Effective Java", "Joshua Bloch"));

        // Save state
        repository.save(library.getAllBooks());

        System.out.println("Library saved successfully.");
    }
}
