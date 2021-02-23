package dabang.star.cafe.domain.user.exception;

import dabang.star.cafe.api.exception.common.ErrorCode;
import dabang.star.cafe.api.exception.common.InvalidValueException;

public class EmailDuplicationException extends InvalidValueException {
    public EmailDuplicationException(String email) {
        super(email, ErrorCode.EMAIL_DUPLICATION);
    }
}
