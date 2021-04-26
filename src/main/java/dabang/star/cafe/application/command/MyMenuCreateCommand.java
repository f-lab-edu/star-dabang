package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.mymenu.MyMenu;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MyMenuCreateCommand {

    @NotNull(message = "invalid name")
    @Size(max = 10, message = "invalid name")
    private String name;

    @NotNull(message = "invalid product id")
    @Positive(message = "invalid product id")
    private Long productId;

    private final Map<String, Integer> optionInfo = new HashMap<>();

    public MyMenu toMyMenu(long memberId) {
        return MyMenu.builder()
                .name(name)
                .memberId(memberId)
                .productId(productId)
                .optionInfo(optionInfo)
                .build();
    }

}
