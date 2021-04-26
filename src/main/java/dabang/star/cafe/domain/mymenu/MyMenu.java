package dabang.star.cafe.domain.mymenu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class MyMenu {

    private Long id;

    private String name;

    private Long productId;

    private Long memberId;

    private Map<String, Integer> optionInfo;

}

