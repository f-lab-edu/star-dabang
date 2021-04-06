package dabang.star.cafe.api;

import dabang.star.cafe.application.exception.DuplicatedException;
import dabang.star.cafe.application.exception.NoAuthenticationException;
import dabang.star.cafe.application.exception.NoAuthorizationException;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 유효성 검증에 대한 예외를 처리하며 Http Status 422을 반환한다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String target = e.getBindingResult()
                .getTarget()
                .toString();
        String errorMessage = e.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(errorMessage)
                .build();

        log.warn("Not Valid Exception : {}", target, e);

        return response;
    }

    /**
     * 중복된 값에 대한 예외를 처리하며 Http Status 409를 반환한다.
     */
    @ExceptionHandler(DuplicatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicatedException(DuplicatedException e) {

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .build();

        log.warn("The requested value is duplicated", e);

        return response;
    }

    /**
     * 요청에 대해 리소스를 찾지 못한 예외를 처리하며 Http Status 404을 반환한다.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(ResourceNotFoundException e) {

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();

        log.warn("No value was found for the request", e);

        return response;
    }


    /**
     * 권한을 갖지 못한 요청에 대한 예외를 처리하며 Http Status 403 을 반환한다.
     */
    @ExceptionHandler(NoAuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleNoAuthorizationException(NoAuthorizationException e) {

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(e.getMessage())
                .build();

        log.warn("No Authorization", e);

        return response;
    }

    /**
     * 인증 실패에 대한 예외를 처리하며 Http Status 401 을 반환한다.
     */
    @ExceptionHandler(NoAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleNoAuthenticationException(NoAuthenticationException e) {

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .build();

        log.warn("No Authentication", e);

        return response;
    }

    @Getter
    @Builder
    public static class ErrorResponse {

        private String message;

        private int status;
    }
}
