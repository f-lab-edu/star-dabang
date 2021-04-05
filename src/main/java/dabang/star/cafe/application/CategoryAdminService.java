package dabang.star.cafe.application;

import dabang.star.cafe.application.data.EnumData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dabang.star.cafe.application.EnumMapperService.CATEGORY_TYPE;

@RequiredArgsConstructor
@Service
public class CategoryAdminService {

    private final EnumMapperService enumMapperService;

    public List<EnumData> getCategoryTypes() {

        return enumMapperService.get(CATEGORY_TYPE);
    }

}
