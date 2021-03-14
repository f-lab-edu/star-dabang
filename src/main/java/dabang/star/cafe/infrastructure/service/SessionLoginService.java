package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.api.exception.NoAuthenticationException;
import dabang.star.cafe.domain.manager.ManagerData;
import dabang.star.cafe.domain.member.MemberData;
import dabang.star.cafe.domain.login.EncryptService;
import dabang.star.cafe.domain.login.LoginService;
import dabang.star.cafe.domain.manager.ManagerRepository;
import dabang.star.cafe.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static dabang.star.cafe.utils.SessionKey.*;

@RequiredArgsConstructor
@Service
public class SessionLoginService implements LoginService {

    private final HttpSession httpSession;
    private final EncryptService encryptService;
    private final MemberRepository memberRepository;
    private final ManagerRepository managerRepository;

    @Override
    public MemberData loginMember(String email, String password) {

        String encryptedPassword = encryptService.encrypt(password);

        Optional<MemberData> byEmailAndPassword = memberRepository.findMemberByEmailAndPassword(email, encryptedPassword);
        MemberData memberData = byEmailAndPassword.orElseThrow(
                () -> new NoAuthenticationException("no authenticated")
        );

        httpSession.setAttribute(LOGIN_MEMBER_ID, memberData.getId());

        return memberData;
    }

    @Override
    public void logoutMember() {
        httpSession.removeAttribute(LOGIN_MEMBER_ID);
    }

    @Override
    public MemberData accessMyPage(long id, String password) {

        String encryptedPassword = encryptService.encrypt(password);

        Optional<MemberData> byIdAndPassword = memberRepository.findMemberByIdAndPassword(id, encryptedPassword);
        MemberData memberData = byIdAndPassword.orElseThrow(
                () -> new NoAuthenticationException("no authenticated")
        );

        httpSession.setAttribute(CURRENT_MEMBER_ID, memberData.getId());

        return memberData;
    }

    @Override
    public ManagerData loginManager(String name, String password) {

        String encryptedPassword = encryptService.encrypt(password);

        Optional<ManagerData> byNameAndPassword = managerRepository.findManagerByNameAndPassword(name, encryptedPassword);
        ManagerData managerData = byNameAndPassword.orElseThrow(
                () -> new NoAuthenticationException("no authenticated")
        );

        httpSession.setAttribute(LOGIN_MANAGER_ID, managerData.getId());
        httpSession.setAttribute(MANAGER_POWER, managerData.getRule());

        return managerData;
    }

    @Override
    public void logoutManager() {
        httpSession.removeAttribute(LOGIN_MANAGER_ID);
        httpSession.removeAttribute(MANAGER_POWER);
    }
}
