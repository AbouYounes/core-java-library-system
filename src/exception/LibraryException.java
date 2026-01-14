package exception;

/**
 * Base exception for library-related errors.
 */
public class LibraryException extends RuntimeException {

    public LibraryException(String message) {
        super(message);
    }
}
