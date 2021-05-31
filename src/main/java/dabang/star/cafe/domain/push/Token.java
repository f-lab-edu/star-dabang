package dabang.star.cafe.domain.push;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Token {

    private Long id;

    private Long memberId;

    private String tokenInfo;

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

}
