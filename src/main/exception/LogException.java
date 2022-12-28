package exception;

////Copied from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
//Exception when printing log
public class LogException extends Exception {
    public LogException() {
        super("Error printing log");
    }

    public LogException(String msg) {
        super(msg);
    }
}
