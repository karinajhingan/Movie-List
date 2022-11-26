package model.exception;


//Exception when printing log
public class LogException extends Exception {
    public LogException() {
        super("Error printing log");
    }

    public LogException(String msg) {
        super(msg);
    }
}
