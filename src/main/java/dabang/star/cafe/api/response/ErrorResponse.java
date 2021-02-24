package dabang.star.cafe.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private String message;

    private int status;
}
