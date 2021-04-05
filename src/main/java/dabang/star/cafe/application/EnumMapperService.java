package dabang.star.cafe.application;

import dabang.star.cafe.application.data.EnumData;
import dabang.star.cafe.domain.category.CategoryType;
import dabang.star.cafe.domain.common.EnumModel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 매번 EnumData로 전환하는 작업을 하지 않고
 * 다른 곳에서 Enum의 리스트를 사용할 때 중복 방지를 위해서 서비스로 등록하여 관리
 */
@Service
public class EnumMapperService {

    public static final String CATEGORY_TYPE = "categoryType";
    private Map<String, List<EnumData>> factory = new HashMap<>();

    @PostConstruct
    public void init() {
        factory.put(CATEGORY_TYPE, toEnumValues(CategoryType.class));
    }

    private List<EnumData> toEnumValues(Class<? extends EnumModel> e) {

        return Arrays.stream(e.getEnumConstants())
                .map(EnumData::new)
                .collect(Collectors.toList());
    }

    public List<EnumData> get(String key) {

        return factory.get(key);
    }

}
