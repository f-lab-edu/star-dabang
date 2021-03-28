package dabang.star.cafe.domain.admin;


import dabang.star.cafe.domain.manager.ManagerData;

public interface ManagerAdminService {

    ManagerData createManager(String name, String password, String officeName);
}
