import exception.LibraryException;
import model.Book;
import service.LibraryService;
import service.LibraryServiceImpl;

public class Main {
    public static void main(String[] args) {
        LibraryService library = new LibraryServiceImpl();

        try {
            library.addBook(new Book("123", "Clean Code", "Robert Martin"));
            library.addBook(new Book("123", "Clean Code", "Robert Martin")); // error
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            library.borrowBook("123");
            library.borrowBook("123"); // error
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
