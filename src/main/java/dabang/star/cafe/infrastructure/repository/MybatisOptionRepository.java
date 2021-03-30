package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
import dabang.star.cafe.infrastructure.mapper.OptionMapper;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public Page<Option> findAll(Pagination pagination) {

        List<Option> options = optionMapper.selectAllOption(pagination.getSize(), pagination.getOffset());
        int totalCount = optionMapper.getAllOptionCount();

        return new Page<>(options, totalCount);
    }

}
