package dabang.star.cafe.application.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyMenuData {

    private Long id;

    private String name;

    private Long productId;

    private Map<Long, Integer> optionInfo;

}
