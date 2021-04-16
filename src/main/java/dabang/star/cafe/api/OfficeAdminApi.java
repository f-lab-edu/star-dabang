package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.application.OfficeAdminService;
import dabang.star.cafe.application.command.OfficeCreateCommand;
import dabang.star.cafe.application.command.OfficeUpdateCommand;
import dabang.star.cafe.application.data.OfficeData;
import dabang.star.cafe.domain.manager.Role;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/offices")
public class OfficeAdminApi {

    private final OfficeAdminService officeAdminService;

    /**
     * 매장 생성
     *
     * @param officeCreateCommand (name, address, latitude, longitude)
     * @return 생성 완료시 HttpStatus.OK (OfficeData) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @PostMapping
    public OfficeData createOffice(@Valid @RequestBody OfficeCreateCommand officeCreateCommand) {
        return officeAdminService.createOffice(officeCreateCommand);
    }

    /**
     * 매장 정보 수정
     *
     * @param officeUpdateCommand (id, name, address, latitude, longitude)
     */
    @LoginCheck(role = Role.ADMIN)
    @PatchMapping
    public void updateOffice(@Valid @RequestBody OfficeUpdateCommand officeUpdateCommand) {
        officeAdminService.updateOffice(officeUpdateCommand);
    }

    /**
     * 매장 삭제
     *
     * @param officeId
     */
    @LoginCheck(role = Role.ADMIN)
    @DeleteMapping("/{officeId}")
    public void deleteOffice(@PathVariable int officeId) {
        officeAdminService.deleteOffice(officeId);
    }

    /**
     * 매장 조회
     *
     * @param pagination
     * @return HttpStatus.OK (List<OfficeData) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @GetMapping
    public Page<OfficeData> getOffices(@ModelAttribute Pagination pagination) {
        return officeAdminService.findOffices(pagination);
    }

}
