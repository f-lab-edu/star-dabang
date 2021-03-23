package dabang.star.cafe.domain.admin;

import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class OptionAdminServiceTest {

    @Autowired
    OptionAdminService optionAdminService;

    @Autowired
    OptionRepository optionRepository;

    @DisplayName("id가 null인 옵션 객체로 새로운 옵션을 추가하고 아이디가 생긴 옵션 객체를 반환한다")
    @Test
    void addOption() {
        Option option = new Option(null, "새로운옵션", 100, 10);

        Option newOption = optionAdminService.createOption(option);

        assertThat(newOption.getId()).isNotNull();
    }

}