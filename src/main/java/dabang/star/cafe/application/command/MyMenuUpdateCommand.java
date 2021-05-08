package dabang.star.cafe.application.command;

import dabang.star.cafe.domain.mymenu.MyMenu;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Getter
@NoArgsConstructor
public class MyMenuUpdateCommand {

    @NotNull(message = "invalid name")
    @Size(max = 10, message = "invalid name")
    private String name;

    private Map<Long, Integer> optionInfo;

    public MyMenu toMyMenu(Long id, Long memberId, long productId) {
        return MyMenu.builder()
                .id(id)
                .name(this.name)
                .memberId(memberId)
                .productId(productId)
                .optionInfo(this.optionInfo)
                .build();
    }

}
