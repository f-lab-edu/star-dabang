package dabang.star.cafe.application;

import dabang.star.cafe.application.command.OfficeCreateCommand;
import dabang.star.cafe.application.command.OfficeUpdateCommand;
import dabang.star.cafe.application.data.OfficeData;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.office.Office;
import dabang.star.cafe.domain.office.OfficeRepository;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OfficeAdminService {

    private final OfficeRepository officeRepository;

    public OfficeData createOffice(OfficeCreateCommand officeCreateCommand) {

        Office office = officeCreateCommand.toOffice();
        officeRepository.save(office);

        return OfficeData.from(office);
    }

    public void deleteOffice(int officeId) {
        officeRepository.deleteById(officeId);
    }

    public void updateOffice(OfficeUpdateCommand officeUpdateCommand) {

        officeRepository.findById(officeUpdateCommand.getId()).orElseThrow(
                () -> new ResourceNotFoundException("office not found")
        );

        officeRepository.save(officeUpdateCommand.toOffice());
    }

    public Page<OfficeData> findOffices(Pagination pagination) {
        return officeRepository.findAll(pagination);
    }
}
