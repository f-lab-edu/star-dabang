package dabang.star.cafe.utils;

import dabang.star.cafe.exception.DuplicatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        log.warn(String.valueOf(e));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity handleDuplicatedException(DuplicatedException e) {

        log.warn(String.valueOf(e));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
