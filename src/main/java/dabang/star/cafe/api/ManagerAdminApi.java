package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.api.request.ManagerCreateRequest;
import dabang.star.cafe.api.request.ManagerUpdateRequest;
import dabang.star.cafe.application.ManagerAdminService;
import dabang.star.cafe.domain.manager.ManagerData;
import dabang.star.cafe.domain.manager.Role;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerAdminApi {

    private final ManagerAdminService managerAdminService;

    /**
     * 매장관리자 등록하기
     *
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

    /**
     * 매장관리자 수정하기
     *
     * @param managerUpdateRequest (id, password, officeName)
     */
    @LoginCheck(role = Role.ADMIN)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateManager(@Valid @RequestBody ManagerUpdateRequest managerUpdateRequest) {
        managerAdminService.updateManager(
                managerUpdateRequest.getId(),
                managerUpdateRequest.getPassword(),
                managerUpdateRequest.getOfficeName()
        );
    }

    /**
     * 매장관리자 삭제하기
     *
     * @param managerId (id)
     */
    @LoginCheck(role = Role.ADMIN)
    @DeleteMapping("/{managerId}")
    public void deleteManager(@PathVariable long managerId) {
        managerAdminService.deleteManager(managerId);
    }

    /**
     * 매장관리자 조회하기
     *
     * @param pagination (page, size)
     * @return Page (List, totalCount) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @GetMapping
    public Page<ManagerData> readManagers(@ModelAttribute Pagination pagination) {
        return managerAdminService.findManagers(pagination);
    }

    /**
     * 매장관리자 검색하기
     *
     * @param name (이름)
     * @return ManagerData List 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @GetMapping("/search")
    public List<ManagerData> searchManagerByName(@RequestParam String name) {
        return managerAdminService.searchManager(name);
    }
}
