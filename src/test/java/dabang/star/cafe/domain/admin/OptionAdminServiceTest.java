package dabang.star.cafe.domain.admin;

import dabang.star.cafe.api.exception.NotFoundException;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
import dabang.star.cafe.infrastructure.mapper.OptionMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
class OptionAdminServiceTest {

    @Autowired
    OptionAdminService optionAdminService;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    OptionMapper optionMapper;

    @DisplayName("id가 null인 옵션 객체로 새로운 옵션을 추가하고 아이디가 생긴 옵션 객체를 반환한다")
    @Test
    void addOptionTest() {
        Option option = new Option(null, "새로운옵션", 100, 10);

        Option newOption = optionAdminService.createOption(option);

        assertThat(newOption.getId()).isNotNull();
    }

    @DisplayName("요청에 대하여 옵션들을 성공적으로 조회하면 List<Option>을 반환한다")
    @Test
    void getAllOptionTest() {
        Option option = new Option(null, "새로운 옵션", 100, 10);
        Option option2 = new Option(null, "새로운 옵션", 100, 10);
        optionRepository.save(option);
        optionRepository.save(option2);

        List<Option> options = optionAdminService.getAllOption();

        assertThat(options.size()).isEqualTo(2);
    }

    @DisplayName("요청에 대하여 온션들을 조회한 결과가 없다면 NotFoundException을 발생시킨다")
    @Test
    void notFoundOptionTest() {

        assertThrows(NotFoundException.class, () -> optionAdminService.getAllOption());
    }

}