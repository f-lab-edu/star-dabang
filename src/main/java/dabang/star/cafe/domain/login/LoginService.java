package dabang.star.cafe.domain.login;

import dabang.star.cafe.api.request.LoginRequest;
import dabang.star.cafe.application.data.MemberId;

public interface LoginService {

<<<<<<< Updated upstream:src/main/java/dabang/star/cafe/domain/member/LoginService.java
    MemberId login(LoginRequest loginRequest);
=======
    Long login(String email, String password);

    void logout();
>>>>>>> Stashed changes:src/main/java/dabang/star/cafe/domain/login/LoginService.java
}
