package dabang.star.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Common {

    /**
     * 상수
     */
    public static final String SESSION_MEMBER_KEY = "MEMBER";

    public static final ResponseEntity RESPONSE_STATUS_OK = ResponseEntity.status(HttpStatus.OK).build();
}
