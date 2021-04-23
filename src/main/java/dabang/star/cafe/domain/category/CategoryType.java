package dabang.star.cafe.domain.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.node.JsonNodeType;
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

    /**
     * 클라이언트의 요청은 간단하게 KEY로 역직렬화
     * "categoryType": "DRINK"
     * ------------------------------------------------------------
     * 클라이언트에 원할함을 위해 KEY-VALUE 쌍으로 제공하기 위해 JSON Object 타입으로 직렬화
     * "categoryType": {
     * "key": "DRINK",
     * "value": "음료"
     * }
     * ------------------------------------------------------------
     * 하지만 캐시에 저장시에도 Object 형으로 직렬화가 되기 때문에 Object 타입을 CategoryType으로 역직렬화 불가
     * 그래서 JsonNode 를 이용해서 Object와 String 타입에 대하여 분기처리하여 두가지 타입에 대하여 직렬화하도록 구현
     */
    @JsonCreator
    public static CategoryType from(JsonNode jsonNode) {
        JsonNodeType nodeType = jsonNode.getNodeType();

        String key;
        if (nodeType == JsonNodeType.OBJECT) {
            key = jsonNode.get("key").textValue();
        } else if (nodeType == JsonNodeType.STRING) {
            key = jsonNode.textValue();
        } else {
            throw new RuntimeJsonMappingException("category type parsing failed");
        }

        return CategoryType.valueOf(key);
    }

}
