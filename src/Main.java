import model.Book;
import service.LibraryService;
import service.LibraryServiceImpl;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LibraryService library = new LibraryServiceImpl();

        Book book = new Book("999", "Effective Java", "Joshua Bloch");
        library.addBook(book);

        library.borrowBook("999");

        Thread.sleep(2000); // simulate time passing

        System.out.println("Borrowed hours: " + book.getBorrowedDurationInHours());
        System.out.println("Created at: " + book.getCreatedAt());
        System.out.println("Borrowed at: " + book.getBorrowedAt());
    }
}
