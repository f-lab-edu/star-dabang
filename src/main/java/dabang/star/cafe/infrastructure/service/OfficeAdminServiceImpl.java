package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.api.exception.OfficeNotFoundException;
import dabang.star.cafe.domain.admin.OfficeAdminService;
import dabang.star.cafe.domain.office.Office;
import dabang.star.cafe.domain.office.OfficeData;
import dabang.star.cafe.domain.office.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class OfficeAdminServiceImpl implements OfficeAdminService {

    private final OfficeRepository officeRepository;

    @Override
    public OfficeData createOffice(String name, String address, BigDecimal latitude, BigDecimal longitude) {

        Office office = Office.builder()
                .name(name)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        officeRepository.save(office);

        return new OfficeData(office);
    }

    @Override
    public void deleteOffice(Long officeId) {
        officeRepository.deleteById(officeId);
    }

    @Override
    public void updateOffice(Long id, String name, String address, BigDecimal latitude, BigDecimal longitude) {

        Office office = officeRepository.findOfficeById(id).orElseThrow(
                () -> new OfficeNotFoundException("office not found")
        );

        officeRepository.save(Office.builder()
                .id(office.getId())
                .name(name)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .build()
        );
    }

}
