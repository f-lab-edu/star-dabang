package dabang.star.cafe.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<ErrorResource> handleInvalidRequest(RuntimeException runtimeException,
                                                              WebRequest request) {
        InvalidRequestException invalidRequestException = (InvalidRequestException) runtimeException;
        List<FieldErrorResource> errorResources = invalidRequestException.getErrors().getFieldErrors().stream()
                .map(fieldError -> new FieldErrorResource(
                        fieldError.getObjectName(),
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getDefaultMessage()
                )).collect(Collectors.toList());

        ErrorResource error = new ErrorResource(errorResources);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(error, httpHeaders, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}