package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.AdminLoginCheck;
import dabang.star.cafe.api.request.OfficeCreateRequest;
import dabang.star.cafe.api.request.OfficeUpdateRequest;
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


    @AdminLoginCheck
    @PostMapping
    public OfficeData createOffice(@Valid @RequestBody OfficeCreateRequest officeCreateRequest) {
        return officeAdminService.createOffice(
                officeCreateRequest.getName(),
                officeCreateRequest.getAddress(),
                officeCreateRequest.getLatitude(),
                officeCreateRequest.getLongitude()
        );
    }

    @AdminLoginCheck
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

    @AdminLoginCheck
    @DeleteMapping("/{officeId}")
    public void deleteOffice(@PathVariable Long officeId) {
        officeAdminService.deleteOffice(officeId);
    }

    @AdminLoginCheck
    @GetMapping
    public List<OfficeData> getOffices(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                       @RequestParam(value = "limit", defaultValue = "20") int limit) {
        return officeAdminService.findOffices(new Page(offset, limit));
    }

}
