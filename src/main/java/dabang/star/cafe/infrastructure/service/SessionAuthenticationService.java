package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.domain.service.AuthenticationService;
import dabang.star.cafe.domain.user.User;
import dabang.star.cafe.domain.user.UserService;
import dabang.star.cafe.infrastructure.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionAuthenticationService implements AuthenticationService {

    private final UserService userService;

    @Override
    public User authenticateByLoginParam(String loginEmail, String loginPassword) {
        User user = userService.loadUserByLoginParam(loginEmail, loginPassword);
        SessionUtils.loginUser(loginEmail);
        return user;
    }

    @Override
    public void removeCurrentUser() {
        SessionUtils.logoutUser();
    }
}
