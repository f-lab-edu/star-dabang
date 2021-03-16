package dabang.star.cafe.api.response.manager;

import dabang.star.cafe.domain.manager.Manager;
import dabang.star.cafe.domain.manager.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ManagerData {

    private Long id;

    private Long officeId;

    private String name;

    private Role role;

    public ManagerData(Manager manager) {
        this.id = manager.getId();
        this.officeId = manager.getOfficeId();
        this.name = manager.getName();
        this.role = manager.getRole();
    }
}
