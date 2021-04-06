package dabang.star.cafe.application.exception;

public class NoAuthorizationException extends RuntimeException {

    public NoAuthorizationException(String message) {
        super(message);
    }
}
