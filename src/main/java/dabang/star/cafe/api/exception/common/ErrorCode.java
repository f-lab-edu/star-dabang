package dabang.star.cafe.api.exception.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE("INVALID_INPUT", "invalid input value"),
    MISMATCH_INPUT_TYPE("TYPE", "mismatch input type"),
    ENTITY_NOT_FOUND("ENTITY_ABSENT", "entity not found"),
    METHOD_NOT_ALLOWED("METHOD", "method not allowed"),
    INTERNAL_SERVER_ERROR("SERVER", "internal server error"),

    // User
    EMAIL_DUPLICATION("EMAIL_DUPLICATED", "duplicated email"),
    USER_NOT_FOUND("USER_ABSENT", "user not found");

    private final String code;
    private final String message;
}
