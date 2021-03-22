package dabang.star.cafe.domain.admin;

import dabang.star.cafe.api.exception.NotFoundException;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
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
    public List<Option> getAllOption() {

        List<Option> options = optionRepository.findAll();

        verifyExitsOption(options);

        return options;
    }

    private void verifyExitsOption(List<Option> options) {

        if (options.size() == 0) {
            throw new NotFoundException("No options were found");
        }
    }

}
