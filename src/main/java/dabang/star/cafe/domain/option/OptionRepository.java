package dabang.star.cafe.domain.option;

import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;

import java.util.List;
import java.util.Optional;

public interface OptionRepository {

    void save(Option option);

    Page<Option> findAll(Pagination pagination);

    List<Option> findByName(String optionName);

    Optional<Option> findById(int id);

    int deleteById(int id);

}
