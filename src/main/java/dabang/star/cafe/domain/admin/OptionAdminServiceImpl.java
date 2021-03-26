package dabang.star.cafe.domain.admin;

import dabang.star.cafe.api.exception.OptionNotFoundException;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
import dabang.star.cafe.utils.Page;
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
    public List<Option> getAllOption(Page page) {
        List<Option> options = optionRepository.findAll(page);

        verifyExitsOption(options);

        return options;
    }

    private void verifyExitsOption(List<Option> options) {

        if (options.size() == 0) {
            throw new OptionNotFoundException("No options were found");
        }
    }

    @Override
    public void updateOption(Option option) {

        optionRepository.findById(option.getId()).orElseThrow(
                () -> new OptionNotFoundException("option not found")
        );

        optionRepository.save(option);
    }

}
