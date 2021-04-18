package dabang.star.cafe.application.exception;

import org.springframework.dao.DuplicateKeyException;

public class DuplicatedException extends RuntimeException {

    public DuplicatedException(String message) {
        super(message);
    }

    public DuplicatedException(Exception cause) {
        super(getMessage(cause), cause);
    }

    private static String getMessage(Exception cause) {
        if (cause instanceof DuplicateKeyException && cause.getCause() != null) {
            return cause.getCause().getMessage();
        }
        return cause.getMessage();
    }

}
