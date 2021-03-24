package dabang.star.cafe.domain.login;

import dabang.star.cafe.api.exception.NoAuthenticationException;
import dabang.star.cafe.domain.manager.ManagerData;
import dabang.star.cafe.domain.manager.ManagerRepository;
import dabang.star.cafe.domain.manager.Role;
import dabang.star.cafe.infrastructure.service.SessionManagerLoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static dabang.star.cafe.utils.SessionKey.LOGIN_ID;
import static dabang.star.cafe.utils.SessionKey.MANAGER_ROLE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class SessionManagerLoginServiceTest {

    private final Long MANAGER_ID = 1L;
    private final Long OFFICE_ID = 1L;
    private final String MANAGER_NAME = "admin";
    private final String ENCRYPTED_PASSWORD = "1q2w3e4r!";
    private final String ORIGINAL_PASSWORD = "11aa";

    @InjectMocks
    SessionManagerLoginService sessionManagerLoginService;

    @Mock
    HttpSession httpSession;

    @Mock
    EncryptService encryptService;

    @Mock
    ManagerRepository managerRepository;

    @DisplayName("로그인을 하기위해 관리자 정보를 조회해서 세션에 아이디와 권한을 저장시킨다")
    @Test
    void successfulLoginTest() {

        ManagerData managerData = new ManagerData(MANAGER_ID, OFFICE_ID, MANAGER_NAME, Role.MANAGER);

        when(encryptService.encrypt(ORIGINAL_PASSWORD)).thenReturn(ENCRYPTED_PASSWORD);
        when(managerRepository.findByNameAndPassword(MANAGER_NAME, ENCRYPTED_PASSWORD))
                .thenReturn(Optional.ofNullable(managerData));

        sessionManagerLoginService.login(MANAGER_NAME, ORIGINAL_PASSWORD);

        verify(httpSession).setAttribute(LOGIN_ID, managerData.getId());
        verify(httpSession).setAttribute(MANAGER_ROLE, managerData.getRole());
    }


    @DisplayName("로그인을 하기위해 조회한 관리자 정보와 다르다면 NoAuthenticationException을 발생시킨다")
    @Test
    void failedLoginTest() {
        when(encryptService.encrypt(ORIGINAL_PASSWORD)).thenReturn(ENCRYPTED_PASSWORD);
        when(managerRepository.findByNameAndPassword(MANAGER_NAME, ENCRYPTED_PASSWORD))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NoAuthenticationException.class, () -> {
            sessionManagerLoginService.login(MANAGER_NAME, ORIGINAL_PASSWORD);
        });
    }

    @DisplayName("로그아웃을 하기위해 세션에 저장된 정보들을 모두 삭제시킨다")
    @Test
    void logoutTest() {
        sessionManagerLoginService.logout();

        verify(httpSession).removeAttribute(LOGIN_ID);
        verify(httpSession).removeAttribute(MANAGER_ROLE);
    }

}