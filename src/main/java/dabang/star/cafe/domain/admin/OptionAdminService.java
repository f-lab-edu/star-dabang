package dabang.star.cafe.domain.admin;

import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;

import java.util.List;

public interface OptionAdminService {

    Option createOption(Option option);

    Page<Option> getAllOption(Pagination pagination);

    List<Option> getOptionByName(String name);
    
    void updateOption(Option option);

    void deleteOption(int optionId);

}
