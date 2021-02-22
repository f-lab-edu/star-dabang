package dabang.star.cafe.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(422, "INPUT", "invalid input value"),
    MISMATCH_INPUT_TYPE(422, "TYPE", "mismatch input type"),
    METHOD_NOT_ALLOWED(405, "METHOD", "method not allowed"),
    INTERNAL_SERVER_ERROR(500, "SERVER", "internal server error"),

    // User
    EMAIL_DUPLICATION(422, "DUPLICATED", "duplicated email"),
    LOGIN_INPUT_INVALID(422, "LOGIN", "invalid login input");

    private final int status;
    private final String code;
    private final String message;
}
