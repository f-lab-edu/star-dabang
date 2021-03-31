package dabang.star.cafe.domain.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManagerData {

    private Long id;

    private Integer officeId;

    private String name;

    private Role role;

    public static ManagerData from(Manager manager) {
        return new ManagerData(manager.getId(), manager.getOfficeId(), manager.getName(), manager.getRole());
    }
}
