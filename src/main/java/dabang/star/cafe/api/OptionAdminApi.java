package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.api.request.OptionCreateRequest;
import dabang.star.cafe.api.request.OptionUpdateRequest;
import dabang.star.cafe.domain.admin.OptionAdminService;
import dabang.star.cafe.domain.manager.Role;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionFactory;
import dabang.star.cafe.utils.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/options")
public class OptionAdminApi {

    private final OptionAdminService optionAdminService;

    /**
     * 새로운 옵션 추가하기
     *
     * @param optionCreateRequest (name, price, quantity)
     * @return 생성 완료시 HttpStatus.CREATED (Option) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Option createOption(@Valid @RequestBody OptionCreateRequest optionCreateRequest) {

        return optionAdminService.createOption(OptionFactory.from(optionCreateRequest));
    }

    /**
     * 새로운 상품을 추가하거나 수정할 때 설정을위해서 옵션 정보를 조회
     *
     * @return 조회 완료시 HttpStatus.OK (List<Option>) 반환
     */
    @LoginCheck(role = Role.ADMIN)
    @GetMapping
    public List<Option> getAllOption(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                     @RequestParam(value = "limit", defaultValue = "20") int limit) {

        return optionAdminService.getAllOption(new Page(offset, limit));
    }

    /**
     * 특정 옵션 정보를 수정
     *
     * @param optionUpdateRequest (id, name, price, maxQuantity)
     */
    @LoginCheck(role = Role.ADMIN)
    @PatchMapping
    public void updateOption(@Valid @RequestBody OptionUpdateRequest optionUpdateRequest) {

        optionAdminService.updateOption(OptionFactory.from(optionUpdateRequest));
    }

    /**
     * 특정 옵션을 삭제
     *
     * @param optionId
     */
    @LoginCheck(role = Role.ADMIN)
    @DeleteMapping("/{optionId}")
    public void deleteOption(@PathVariable Integer optionId) {

        optionAdminService.deleteOption(optionId);
    }

}
