package dabang.star.cafe.api.exception;

public class NoAuthorizationException extends RuntimeException {

    public NoAuthorizationException(String message) {
        super(message);
    }
}
