package dabang.star.cafe.domain.authentication;

import dabang.star.cafe.domain.manager.ManagerData;

public interface ManagerLoginService {

    ManagerData login(String name, String password);

    void logout();
}
