package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
import dabang.star.cafe.infrastructure.mapper.OptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MybatisOptionRepository implements OptionRepository {

    private final OptionMapper optionMapper;

    public void save(Option option) {

        if (option.getId() == null) {
            optionMapper.insert(option);
        } else {
            optionMapper.update(option);
        }
    }

}
