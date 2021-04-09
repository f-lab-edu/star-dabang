package dabang.star.cafe.domain.category;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum CategoryType {

    DRINK("음료"),
    FOOD("음식");

    private String key;
    private String value;

    CategoryType(String value) {
        this.key = name();
        this.value = value;
    }

}
