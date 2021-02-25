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
     * 유효성 검증에 대한 예외를 처리하며 Http Status 422을 반환한다.
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
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(errorMessage)
                .build();

        log.warn("Not Valid Exception : {}", target, e);

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
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
     * 요청에 대해 멤버를 찾지 못한 예외를 처리하며 Http Status 404을 반환한다.
     */
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(MemberNotFoundException e) {

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();

        log.warn("Member Not Found", e);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    /**
     * 권한을 갖지 못한 요청에 대한 예외를 처리하며 Http Status 403 을 반환한다.
     */
    @ExceptionHandler(NoAuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleNoAuthorizationException(NoAuthorizationException e) {

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(e.getMessage())
                .build();

        log.warn("No Authorization", e);

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
