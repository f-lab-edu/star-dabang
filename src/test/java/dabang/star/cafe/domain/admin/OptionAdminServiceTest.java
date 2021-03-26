package dabang.star.cafe.domain.admin;

import dabang.star.cafe.api.exception.OptionNotFoundException;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.utils.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class OptionAdminServiceTest {

    @Autowired
    OptionAdminService optionAdminService;

    private final String OPTION_NAME = "새로운 옵션";
    private final int PRICE = 100;
    private final int MAX_QUANTITY = 10;
    private final int DEFAULT_OFFSET = 0;
    private final int DEFAULT_LIMIT = 20;

    @DisplayName("id가 null인 옵션 객체로 새로운 옵션을 추가하고 아이디가 생긴 옵션 객체를 반환한다")
    @Test
    void addOptionTest() {
        Option option = new Option(null, OPTION_NAME, PRICE, MAX_QUANTITY);

        Option newOption = optionAdminService.createOption(option);

        assertThat(newOption.getId()).isNotNull();
    }

    @DisplayName("요청에 대하여 옵션들을 성공적으로 조회하면 옵션 목록을 반환한다")
    @Test
    void getAllOptionTest() {
        Option option = new Option(null, "새로운 옵션1", PRICE, MAX_QUANTITY);
        Option option2 = new Option(null, "새로운 옵션2", PRICE, MAX_QUANTITY);
        optionAdminService.createOption(option);
        optionAdminService.createOption(option2);

        List<Option> options = optionAdminService.getAllOption(new Page(DEFAULT_OFFSET, DEFAULT_LIMIT));

        assertThat(options.size()).isEqualTo(2);
    }

    @DisplayName("요청에 대하여 온션들을 조회한 결과가 없다면 OptionNotFoundException을 발생시킨다")
    @Test
    void notFoundOptionTest() {

        assertThrows(OptionNotFoundException.class,
                () -> optionAdminService.getAllOption(new Page(DEFAULT_OFFSET, DEFAULT_LIMIT)));
    }

    @DisplayName("옵션의 정보 수정할 때 존재하지 않는 옵션의 id라면 OptionNotFoundException을 발생시킨다")
    @Test
    void failOptionUpdateTest() {
        Option newOption = new Option(1, "바뀐 옵션", PRICE, MAX_QUANTITY);

        assertThrows(OptionNotFoundException.class,
                () -> optionAdminService.updateOption(newOption));
    }

    @DisplayName("옵션의 정보를 수정할 때 존재하는 옵션의 id라면 성공적으로 정보를 수정한다")
    @Test
    void successOptionUpdateTest() {
        Option originOption = new Option(null, OPTION_NAME, PRICE, MAX_QUANTITY);
        optionAdminService.createOption(originOption);

        Option newOption = new Option(originOption.getId(), "바뀐 옵션", 300, 30);
        optionAdminService.updateOption(newOption);

        List<Option> findOptions = optionAdminService.getAllOption(new Page(DEFAULT_OFFSET, DEFAULT_LIMIT));
        Option findOption = findOptions.get(0);

        assertThat(findOption.getId()).isEqualTo(originOption.getId());
        assertThat(findOption.getName()).isEqualTo("바뀐 옵션");
        assertThat(findOption.getPrice()).isEqualTo(300);
        assertThat(findOption.getMaxQuantity()).isEqualTo(30);
    }

}