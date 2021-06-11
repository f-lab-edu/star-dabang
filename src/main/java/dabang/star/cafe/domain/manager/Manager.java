package dabang.star.cafe.domain.manager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Manager {

    private Long id;

    private Integer officeId;

    private String name;

    private String password;

    private Role role;

    private String token;

}
