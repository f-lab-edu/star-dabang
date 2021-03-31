package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.api.request.ManagerCreateRequest;
import dabang.star.cafe.domain.admin.ManagerAdminService;
import dabang.star.cafe.domain.manager.ManagerData;
import dabang.star.cafe.domain.manager.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerAdminApi {

    private final ManagerAdminService managerAdminService;

    /**
     * 매장관리자 등록하기
     * @param managerCreateRequest (name, password, officeName)
     * @return 등록 완료시 HttpStatus.CREATED (ManagerData) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ManagerData registerManager(@Valid @RequestBody ManagerCreateRequest managerCreateRequest) {
        return managerAdminService.createManager(
                managerCreateRequest.getName(),
                managerCreateRequest.getPassword(),
                managerCreateRequest.getOfficeName()
        );
    }
}
