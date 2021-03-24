package dabang.star.cafe.domain.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManagerData {

    private Long id;

    private Long officeId;

    private String name;

    private Role role;

}
