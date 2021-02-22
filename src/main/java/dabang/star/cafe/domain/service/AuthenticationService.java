package dabang.star.cafe.domain.service;

import dabang.star.cafe.domain.user.User;

public interface AuthenticationService {
    User authenticateByLoginParam(String loginEmail, String loginPassword);

    void removeCurrentUser();
}
