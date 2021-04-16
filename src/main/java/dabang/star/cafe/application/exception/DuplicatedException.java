package dabang.star.cafe.application.exception;

public class DuplicatedException extends RuntimeException {

    public DuplicatedException(String message) {
        super(message);
    }

    public DuplicatedException(Exception cause) {
        super(cause);
    }
}
