package dabang.star.cafe.application.data;

import dabang.star.cafe.domain.common.EnumModel;

public class EnumData {

    private String key;
    private String value;

    public EnumData(EnumModel enumModel) {
        key = enumModel.getKey();
        value = enumModel.getValue();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
