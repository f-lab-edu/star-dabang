package dabang.star.cafe.domain.common;

/**
 * 자바의 다형성을 이용하여 여러 enum들을 key-value DTO로 사용할 수 있도록 인터페이스로 추상화
 */
public interface EnumModel {

    String getKey();

    String getValue();

}
