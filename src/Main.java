import model.Book;
import service.LibraryService;

public class Main {
    public static void main(String[] args) {
        LibraryService library = new LibraryService();

        Book b1 = new Book("123", "Clean Code", "Robert Martin");
        Book b2 = new Book("123", "Clean Code", "Robert Martin");

        System.out.println(library.addBook(b1)); // true
        System.out.println(library.addBook(b2)); // false

        Book found = library.findBookByIsbn("123");
        System.out.println(found.getTitle()); // Clean Code
    }
}
