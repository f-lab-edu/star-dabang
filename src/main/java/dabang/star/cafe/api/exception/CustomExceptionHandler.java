package dabang.star.cafe.api.exception;

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
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String target = e.getBindingResult()
                .getTarget()
                .toString();
        String errorMessage = e.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        log.warn("Not Valid Exception : {}", target, e);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    /**
     * 유니크한 값에 대하여 중복된 값으로 요청시에 에러가 발생한다
     */
    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity handleDuplicatedException(DuplicatedException e) {

        log.warn("The requested value is duplicated", e);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(NotAuthenticationException.class)
    public ResponseEntity handleNotAuthenticationException(NotAuthenticationException e) {

        log.warn("User failed authentication", e);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
