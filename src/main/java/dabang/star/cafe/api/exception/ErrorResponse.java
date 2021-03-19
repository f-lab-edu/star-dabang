package dabang.star.cafe.api.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private String message;

    private int status;
}
