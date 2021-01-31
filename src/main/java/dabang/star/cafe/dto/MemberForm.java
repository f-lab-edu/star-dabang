package dabang.star.cafe.dto;

import lombok.Data;

@Data
public class MemberForm {

    private final String email;

    private final String passwd;

    private final String nickname;

    private final String telephone;

    private final String birth;
}
