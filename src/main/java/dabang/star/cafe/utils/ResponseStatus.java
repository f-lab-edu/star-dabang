package dabang.star.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseStatus {

    public static final ResponseEntity OK = ResponseEntity.status(HttpStatus.OK).build();
}
