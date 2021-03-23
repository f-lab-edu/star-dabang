package dabang.star.cafe.domain.admin;

import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OptionAdminServiceImpl implements OptionAdminService {

    private final OptionRepository optionRepository;

    @Override
    public Option createOption(Option option) {

        optionRepository.save(option);

        return option;
    }

}
