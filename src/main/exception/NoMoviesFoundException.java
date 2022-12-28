package exception;

public class NoMoviesFoundException extends Exception {
    public NoMoviesFoundException(String msg) {
        System.out.println(msg);
    }
}
