package dabang.star.cafe.domain.mymenu;

import lombok.*;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyMenu {

    private Long id;

    private String name;

    private Long productId;

    private Long memberId;

    private Map<Long, Integer> optionInfo;

}

