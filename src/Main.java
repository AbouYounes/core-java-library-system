import model.Book;
import service.LibraryService;

public class Main {
    public static void main(String[] args) {
        LibraryService library = new LibraryService();

        library.addBook(new Book("111", "Effective Java", "Joshua Bloch"));
        library.addBook(new Book("222", "Clean Code", "Robert Martin"));
        library.addBook(new Book("333", "Design Patterns", "Erich Gamma"));

        library.findBookByIsbn("222").borrow();

        System.out.println("Books sorted by title:");
        library.getBooksSortedByTitle()
                .forEach(book -> System.out.println(book.getTitle()));

        System.out.println("\nAvailable books:");
        library.getAvailableBooks()
                .forEach(book -> System.out.println(book.getTitle()));
    }
}
