package dabang.star.cafe.domain.option;

import dabang.star.cafe.utils.Page;

import java.util.List;
import java.util.Optional;

public interface OptionRepository {

    void save(Option option);

    List<Option> findAll(Page page);

    Optional<Option> findById(int id);

    int deleteById(int id);

}
