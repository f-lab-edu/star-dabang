package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
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

    @Autowired
    OptionRepository optionRepository;

    @DisplayName("id가 null인 option을 저장하면 id를 생성해 반환한다")
    @Test
    void insertOptionTest() {
        Option option = new Option(null, "name", 100, 10);

        optionRepository.save(option);

        assertThat(option.getId()).isNotNull();
    }
}