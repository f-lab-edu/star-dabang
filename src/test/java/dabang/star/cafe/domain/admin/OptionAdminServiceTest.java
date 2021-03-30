package dabang.star.cafe.domain.admin;

import dabang.star.cafe.api.exception.OptionNotFoundException;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

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
    private final int DEFAULT_PAGE = 0;
    private final int DEFAULT_SIZE = 20;

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

        Page<Option> optionPage = optionAdminService.getAllOption(new Pagination(DEFAULT_PAGE, DEFAULT_SIZE));

        assertThat(optionPage.getContent().size()).isEqualTo(2);
        assertThat(optionPage.getTotalCount()).isEqualTo(2);
    }

    @DisplayName("요청에 대하여 온션들을 조회한 결과가 없다면 OptionNotFoundException을 발생시킨다")
    @Test
    void notFoundOptionTest() {

        assertThrows(OptionNotFoundException.class,
                () -> optionAdminService.getAllOption(new Pagination(DEFAULT_PAGE, DEFAULT_SIZE)));
    }

}