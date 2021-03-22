package dabang.star.cafe.domain.option;

import java.util.List;

public interface OptionRepository {

    void save(Option option);

    List<Option> findAll();

}
