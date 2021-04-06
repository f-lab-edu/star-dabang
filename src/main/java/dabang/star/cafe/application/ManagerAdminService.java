package dabang.star.cafe.application;

import dabang.star.cafe.application.command.ManagerCreateCommand;
import dabang.star.cafe.application.command.ManagerUpdateCommand;
import dabang.star.cafe.application.data.ManagerData;
import dabang.star.cafe.application.data.OfficeData;
import dabang.star.cafe.application.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.authentication.EncryptService;
import dabang.star.cafe.domain.manager.Manager;
import dabang.star.cafe.domain.manager.ManagerRepository;
import dabang.star.cafe.domain.office.OfficeRepository;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerAdminService {

    private final EncryptService encryptService;

    private final ManagerRepository managerRepository;

    private final OfficeRepository officeRepository;

    public ManagerData createManager(ManagerCreateCommand managerCreateCommand) {

        OfficeData officeData = officeRepository.findByName(managerCreateCommand.getOfficeName()).orElseThrow(
                () -> new ResourceNotFoundException("office not found.")
        );
        String encryptedPassword = encryptService.encrypt(managerCreateCommand.getPassword());

        Manager manager = managerCreateCommand.toManager(officeData.getId(), encryptedPassword);
        managerRepository.save(manager);

        return ManagerData.from(manager);
    }

    public void updateManager(ManagerUpdateCommand managerUpdateCommand) {

        OfficeData officeData = officeRepository.findByName(managerUpdateCommand.getOfficeName()).orElseThrow(
                () -> new ResourceNotFoundException("office not found.")
        );

        String encryptedPassword = encryptService.encrypt(managerUpdateCommand.getPassword());
        managerRepository.findById(managerUpdateCommand.getId()).map(manager -> {
            managerRepository.save(managerUpdateCommand.toManager(officeData.getId(), encryptedPassword));
            return manager;
        }).orElseThrow(() -> new ResourceNotFoundException("manager not found."));
    }

    public void deleteManager(long id) {
        managerRepository.deleteById(id);
    }

    public Page<ManagerData> findManagers(Pagination pagination) {
        return managerRepository.findAll(pagination);
    }

    public List<ManagerData> searchManager(String name) {
        return managerRepository.findByName(name);
    }
}
