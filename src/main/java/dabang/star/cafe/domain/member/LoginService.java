package dabang.star.cafe.domain.member;

import dabang.star.cafe.api.request.LoginRequest;
import dabang.star.cafe.application.data.MemberId;

public interface LoginService {

    MemberId login(LoginRequest loginRequest);
}
