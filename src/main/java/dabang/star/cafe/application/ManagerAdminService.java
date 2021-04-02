package dabang.star.cafe.application;

import dabang.star.cafe.api.exception.OfficeNotFoundException;
import dabang.star.cafe.api.exception.ResourceNotFoundException;
import dabang.star.cafe.domain.authentication.EncryptService;
import dabang.star.cafe.domain.manager.Manager;
import dabang.star.cafe.domain.manager.ManagerData;
import dabang.star.cafe.domain.manager.ManagerRepository;
import dabang.star.cafe.domain.manager.Role;
import dabang.star.cafe.domain.office.OfficeData;
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

    public ManagerData createManager(String name, String password, String officeName) {

        OfficeData officeData = officeRepository.findByName(officeName).orElseThrow(
                () -> new OfficeNotFoundException("office not found.")
        );

        String encryptedPassword = encryptService.encrypt(password);
        Manager manager = Manager.builder()
                .officeId(officeData.getId())
                .name(name)
                .password(encryptedPassword)
                .role(Role.MANAGER)
                .build();
        managerRepository.save(manager);

        return ManagerData.from(manager);
    }

    public void updateManager(long id, String password, String officeName) {
        OfficeData officeData = officeRepository.findByName(officeName).orElseThrow(
                () -> new OfficeNotFoundException("office not found.")
        );

        managerRepository.findById(id).map(manager -> {
            managerRepository.save(Manager.builder()
                    .id(id)
                    .name(manager.getName())
                    .password(password)
                    .officeId(officeData.getId())
                    .build()
            );
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
