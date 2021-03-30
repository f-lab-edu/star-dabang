package dabang.star.cafe.domain.option;

import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;

import java.util.List;

public interface OptionRepository {

    void save(Option option);

    Page<Option> findAll(Pagination pagination);

    List<Option> findByName(String optionName);
}
