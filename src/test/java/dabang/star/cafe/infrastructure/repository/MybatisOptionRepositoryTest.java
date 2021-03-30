package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@MybatisTest
@Import(MybatisOptionRepository.class)
class MybatisOptionRepositoryTest {

    private final String OPTION_NAME = "새로운 옵션";
    private final int PRICE = 100;
    private final int MAX_QUANTITY = 10;
    private final int DEFAULT_PAGE = 0;
    private final int DEFAULT_SIZE = 20;

    @Autowired
    OptionRepository optionRepository;

    @DisplayName("id가 null인 option을 저장하면 id를 생성해 반환한다")
    @Test
    void insertOptionTest() {
        Option option = new Option(null, OPTION_NAME, PRICE, MAX_QUANTITY);

        optionRepository.save(option);

        assertThat(option.getId()).isNotNull();
    }

    @DisplayName("옵션을 성공적으로 조회하면 옵션 목록을 반환한다")
    @Test
    void selectAllOptionTest() {
        Option option = new Option(null, "새로운 옵션1", PRICE, MAX_QUANTITY);
        Option option2 = new Option(null, "새로운 옵션2", PRICE, MAX_QUANTITY);
        optionRepository.save(option);
        optionRepository.save(option2);

        Page<Option> optionPage = optionRepository.findAll(new Pagination(DEFAULT_PAGE, DEFAULT_SIZE));

        Assertions.assertThat(optionPage.getContent().size()).isEqualTo(2);
        Assertions.assertThat(optionPage.getTotalCount()).isEqualTo(2);
    }

}