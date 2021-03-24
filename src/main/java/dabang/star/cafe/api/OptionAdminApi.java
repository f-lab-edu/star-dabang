package dabang.star.cafe.api;

import dabang.star.cafe.api.aop.LoginCheck;
import dabang.star.cafe.api.request.OptionCreateRequest;
import dabang.star.cafe.domain.admin.OptionAdminService;
import dabang.star.cafe.domain.manager.Role;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

}