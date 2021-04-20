package dabang.star.cafe.api;

import dabang.star.cafe.application.OfficeQueryService;
import dabang.star.cafe.application.command.OfficeSearchCommand;
import dabang.star.cafe.application.data.OfficeSearchData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offices")
public class OfficeSearchApi {

    private final OfficeQueryService officeQueryService;

    /**
     * 현재 위치를 받아 주변 매장을 검색하는 API
     *
     * @param officeSearchCommand (curLatitude, curLongitude)
     * @return 검색된 인근의 매장 정보 반환
     */
    @PostMapping("/search")
    public List<OfficeSearchData> nearByOffice(@Valid @RequestBody OfficeSearchCommand officeSearchCommand) {
        return officeQueryService.searchOfficeByDistanceFromCurrentLocation(officeSearchCommand);
    }

}
