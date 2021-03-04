package dabang.star.cafe.api.exception;

public class NoAuthenticationException extends RuntimeException {

    public NoAuthenticationException(String message) {
        super(message);
    }
}
