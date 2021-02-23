package dabang.star.cafe.api.exception.common;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String target) {
        super(target, ErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(String target, ErrorCode errorCode) {
        super(target, errorCode);
    }


}
