package dabang.star.cafe.domain.user.exception;

import dabang.star.cafe.api.exception.common.EntityNotFoundException;
import dabang.star.cafe.api.exception.common.ErrorCode;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String target) {
        super(target + "is not found", ErrorCode.USER_NOT_FOUND);
    }
}
