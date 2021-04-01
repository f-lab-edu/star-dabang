package dabang.star.cafe.domain.admin;

import dabang.star.cafe.api.exception.OptionNotFoundException;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OptionAdminServiceImpl implements OptionAdminService {

    private final OptionRepository optionRepository;

    @Override
    public Option createOption(Option option) {

        optionRepository.save(option);

        return option;
    }

    @Override
    public Page<Option> getAllOption(Pagination pagination) {

        Page<Option> optionPage = optionRepository.findAll(pagination);
        verifyExistsOption(optionPage.getContent());

        return optionPage;
    }

    private void verifyExistsOption(List<Option> options) {

        if (options.size() == 0) {
            throw new OptionNotFoundException("No options were found");
        }
    }

    @Override
    public List<Option> getOptionByName(String optionName) {

        List<Option> options = optionRepository.findByName(optionName);
        verifyExistsOption(options);

        return options;
    }

    @Override
    public void updateOption(Option option) {

        loadById(option.getId());

        optionRepository.save(option);
    }

    private Option loadById(int optionId) {

        return optionRepository.findById(optionId).orElseThrow(
                () -> new OptionNotFoundException("option not found by id : " + optionId)
        );
    }

    @Override
    public void deleteOption(int optionId) {

        if (optionRepository.deleteById(optionId) == 0) {
            throw new OptionNotFoundException("option not found by id : " + optionId);
        }

    }

}
