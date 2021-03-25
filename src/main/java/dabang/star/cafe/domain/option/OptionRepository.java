package dabang.star.cafe.domain.option;

import dabang.star.cafe.utils.Page;

import java.util.List;

public interface OptionRepository {

    void save(Option option);

    List<Option> findAll(Page page);

}
