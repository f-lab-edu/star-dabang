package dabang.star.cafe.api.exception;

import dabang.star.cafe.api.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 유효하지 않은 요청 값에 대하여 에러가 발생한다
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String target = e.getBindingResult()
                .getTarget()
                .toString();
        String errorMessage = e.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage)
                .build();

        log.warn("Not Valid Exception : {}", target, e);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 유니크한 값에 대하여 중복된 값으로 요청시에 에러가 발생한다
     */
    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedException(DuplicatedException e) {

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .build();

        log.warn("The requested value is duplicated", e);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleNotAuthenticationException(NotAuthenticationException e) {

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .build();

        log.warn("User failed authentication", e);

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
