package dabang.star.cafe.application.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyMenuInfoData {

    private Long myMenuId;

    private String myMenuName;

    private ProductData product;

}
