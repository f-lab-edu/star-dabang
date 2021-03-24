package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.api.request.OfficeCreateRequest;
import dabang.star.cafe.api.request.OfficeUpdateRequest;
import dabang.star.cafe.domain.manager.Role;
import dabang.star.cafe.domain.office.OfficeData;
import dabang.star.cafe.domain.admin.OfficeAdminService;
import dabang.star.cafe.utils.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/offices")
public class OfficeAdminApi {

    private final OfficeAdminService officeAdminService;

    /**
     * 매장 생성
     *
     * @param officeCreateRequest (name, address, latitude, longitude)
     * @return 생성 완료시 HttpStatus.OK (OfficeData) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @PostMapping
    public OfficeData createOffice(@Valid @RequestBody OfficeCreateRequest officeCreateRequest) {
        return officeAdminService.createOffice(
                officeCreateRequest.getName(),
                officeCreateRequest.getAddress(),
                officeCreateRequest.getLatitude(),
                officeCreateRequest.getLongitude()
        );
    }

    /**
     * 매장 정보 수정
     *
     * @param officeUpdateRequest (id, name, address, latitude, longitude)
     */
    @LoginCheck(role = Role.ADMIN)
    @PatchMapping
    public void updateOffice(@Valid @RequestBody OfficeUpdateRequest officeUpdateRequest) {
        officeAdminService.updateOffice(
                officeUpdateRequest.getId(),
                officeUpdateRequest.getName(),
                officeUpdateRequest.getAddress(),
                officeUpdateRequest.getLatitude(),
                officeUpdateRequest.getLongitude()
        );
    }

    /**
     * 매장 삭제
     *
     * @param officeId
     */
    @LoginCheck(role = Role.ADMIN)
    @DeleteMapping("/{officeId}")
    public void deleteOffice(@PathVariable Long officeId) {
        officeAdminService.deleteOffice(officeId);
    }

    /**
     * 매장 조회
     * @param offset
     * @param limit
     * @return HttpStatus.OK (List<OfficeData) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @GetMapping
    public List<OfficeData> getOffices(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                       @RequestParam(value = "limit", defaultValue = "20") int limit) {
        return officeAdminService.findOffices(new Page(offset, limit));
    }

}
