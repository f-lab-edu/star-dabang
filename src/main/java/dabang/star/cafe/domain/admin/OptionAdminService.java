package dabang.star.cafe.domain.admin;

import dabang.star.cafe.domain.option.Option;

import java.util.List;

public interface OptionAdminService {

    Option createOption(Option option);

    List<Option> getAllOption();

}
