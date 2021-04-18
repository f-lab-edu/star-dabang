package dabang.star.cafe.application;

import dabang.star.cafe.application.command.OptionCreateCommand;
import dabang.star.cafe.application.command.OptionUpdateCommand;
import dabang.star.cafe.application.exception.DuplicatedException;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.domain.option.OptionRepository;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OptionAdminService {

    private final OptionRepository optionRepository;

    public Option createOption(OptionCreateCommand optionCreateCommand) {

        Option option = optionCreateCommand.toOption();

        try {
            optionRepository.save(option);
        } catch (DuplicateKeyException e) {
            throw new DuplicatedException(e);
        }

        return option;
    }

    public Page<Option> getAllOption(Pagination pagination) {
        return optionRepository.findAll(pagination);
    }

    public List<Option> getOptionByName(String optionName) {
        return optionRepository.findByName(optionName);
    }

    public void updateOption(OptionUpdateCommand optionUpdateCommand) {

        if (optionRepository.existsById(optionUpdateCommand.getId())) {
            Option option = optionUpdateCommand.toOption();
            optionRepository.save(option);
        } else {
            throw new ResourceNotFoundException("option not found by id: " + optionUpdateCommand.getId());
        }
    }

    public void deleteOption(long optionId) {

        if (optionRepository.deleteById(optionId) == 0) {
            throw new ResourceNotFoundException("option not found by id : " + optionId);
        }

    }

}
