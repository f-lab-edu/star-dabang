package dabang.star.cafe.domain.authentication;

import dabang.star.cafe.application.data.ManagerData;

public interface ManagerLoginService {

    ManagerData login(String name, String password);

    void logout();
}
