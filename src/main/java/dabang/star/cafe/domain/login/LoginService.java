package dabang.star.cafe.domain.login;

import dabang.star.cafe.application.data.MemberId;

public interface LoginService {

    MemberId login(String email, String password);

    void logout();
}
