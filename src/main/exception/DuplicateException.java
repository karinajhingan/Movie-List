package exception;

public class DuplicateException extends NoMoviesFoundException {
    public DuplicateException(String title) {
        super("'" + title + "'" + " is already in your list");

    }
}
