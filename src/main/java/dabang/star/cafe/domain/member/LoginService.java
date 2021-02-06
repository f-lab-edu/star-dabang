package dabang.star.cafe.domain.member;

import dabang.star.cafe.api.request.LoginRequest;

public interface LoginService {

    Long login(LoginRequest loginRequest);
}
