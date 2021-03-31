package dabang.star.cafe.domain.admin;


import dabang.star.cafe.domain.manager.ManagerData;

public interface ManagerAdminService {

    ManagerData createManager(String name, String password, String officeName);

    void updateManager(long id, String password, String officeName);

    void deleteManager(long id);
}
