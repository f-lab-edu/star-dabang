package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
import dabang.star.cafe.infrastructure.mapper.OptionMapper;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

        int size = pagination.getSize();
        int offset = pagination.getOffset();
        int page = pagination.getPage();

        List<Option> options = optionMapper.selectAllOption(size, offset);
        int totalCount = optionMapper.getAllOptionCount();

        return new Page<>(options, totalCount, size, page);
    }

    @Override
    public List<Option> findByName(String name) {

        return optionMapper.selectByName(name);
    }

    @Override
    public Optional<Option> findById(int id) {

        return optionMapper.getById(id);
    }

    @Override
    public int deleteById(int id) {

        return optionMapper.removeById(id);
    }

}
