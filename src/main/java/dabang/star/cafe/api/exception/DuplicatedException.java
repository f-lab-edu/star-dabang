package dabang.star.cafe.api.exception;

public class DuplicatedException extends RuntimeException {

    public DuplicatedException(String message) {
        super(message);
    }
}
