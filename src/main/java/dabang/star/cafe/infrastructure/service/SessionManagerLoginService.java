package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.api.exception.NoAuthenticationException;
import dabang.star.cafe.domain.login.EncryptService;
import dabang.star.cafe.domain.login.ManagerLoginService;
import dabang.star.cafe.domain.manager.ManagerData;
import dabang.star.cafe.domain.manager.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static dabang.star.cafe.utils.SessionKey.LOGIN_ID;
import static dabang.star.cafe.utils.SessionKey.MANAGER_ROLE;

@RequiredArgsConstructor
@Service
public class SessionManagerLoginService implements ManagerLoginService {

    private final HttpSession httpSession;
    private final EncryptService encryptService;
    private final ManagerRepository managerRepository;

    @Override
    public ManagerData login(String name, String password) {
        String encryptedPassword = encryptService.encrypt(password);

        Optional<ManagerData> byNameAndPassword = managerRepository.findByNameAndPassword(name, encryptedPassword);
        ManagerData managerData = byNameAndPassword.orElseThrow(
                () -> new NoAuthenticationException("no authenticated")
        );

        httpSession.setAttribute(LOGIN_ID, managerData.getId());
        httpSession.setAttribute(MANAGER_ROLE, managerData.getRole());

        return managerData;
    }

    @Override
    public void logout() {
        httpSession.removeAttribute(LOGIN_ID);
        httpSession.removeAttribute(MANAGER_ROLE);
    }
}
