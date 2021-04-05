package dabang.star.cafe.domain.category;

import dabang.star.cafe.domain.common.EnumModel;

public enum CategoryType implements EnumModel {

    DRINK("음료"),
    FOOD("음식");

    private String value;

    CategoryType(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }

}
