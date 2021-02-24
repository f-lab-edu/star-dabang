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
     * 유효성 검증에 대한 예외를 처리하며 Http Status 400을 반환한다.
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
     * 중복된 값에 대한 예외를 처리하며 Http Status 409를 반환한다.
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

    /**
     * 로그인 인증 실패에 대한 예외를 처리하며 Http Status 401을 반환한다.
     */
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotAuthenticationException(MemberNotFoundException e) {

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .build();

        log.warn("User failed authentication", e);

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
