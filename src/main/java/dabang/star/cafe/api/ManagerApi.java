package dabang.star.cafe.api;

import dabang.star.cafe.api.request.ManagerLoginRequest;
import dabang.star.cafe.application.data.ManagerData;
import dabang.star.cafe.domain.authentication.ManagerLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/managers")
public class ManagerApi {

    private final ManagerLoginService managerLoginService;

    /**
     * 관리자 로그인
     *
     * @param managerLoginRequest (name, password)
     * @return 로그인 완료시 HttpStatus.OK (ManagerData) 반환
     */
    @PostMapping("/login")
    public ManagerData loginManager(@Valid @RequestBody ManagerLoginRequest managerLoginRequest) {

        return managerLoginService.login(managerLoginRequest.getName(), managerLoginRequest.getPassword());
    }


    /**
     * 관리자 로그아웃
     * <p>
     * 로그아웃 완료시 HttpStatus.OK 반환
     */
    @PostMapping("/logout")
    public void logoutMember() {
        managerLoginService.logout();
    }

}
