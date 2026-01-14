import model.Book;
import service.LibraryFileRepository;
import service.LibraryService;
import service.LibraryServiceImpl;

public class Main {
    public static void main(String[] args) {
        LibraryServiceImpl library = new LibraryServiceImpl();
        LibraryFileRepository repository = new LibraryFileRepository("library.txt");

        repository.load(library);

        library.addBook(new Book("103", "Clean Architecture", "Robert Martin"));
        library.addBook(new Book("104", "Effective Java", "Joshua Bloch"));

        repository.save(library.getAllBooks().values());

        System.out.println("Library saved successfully.");
    }
}
