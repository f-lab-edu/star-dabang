package dabang.star.cafe.domain.admin;


import dabang.star.cafe.domain.manager.ManagerData;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;

import java.util.List;

public interface ManagerAdminService {

    ManagerData createManager(String name, String password, String officeName);

    void updateManager(long id, String password, String officeName);

    void deleteManager(long id);

    Page<ManagerData> findManagers(Pagination pagination);

    List<ManagerData> searchManager(String name);
}
