package dabang.star.cafe.application;

import dabang.star.cafe.api.exception.OfficeNotFoundException;
import dabang.star.cafe.domain.office.Office;
import dabang.star.cafe.domain.office.OfficeData;
import dabang.star.cafe.domain.office.OfficeRepository;
import dabang.star.cafe.infrastructure.mapper.OfficeReadService;
import dabang.star.cafe.utils.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OfficeAdminService {

    private final OfficeRepository officeRepository;

    private final OfficeReadService officeReadService;

    public OfficeData createOffice(String name, String address, BigDecimal latitude, BigDecimal longitude) {

        Office office = Office.builder()
                .name(name)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        officeRepository.save(office);

        return OfficeData.from(office);
    }

    public void deleteOffice(int officeId) {
        officeRepository.deleteById(officeId);
    }

    public void updateOffice(int id, String name, String address, BigDecimal latitude, BigDecimal longitude) {

        Office office = officeRepository.findById(id).orElseThrow(
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

    public List<OfficeData> findOffices(Page page) {
        return officeReadService.findOffices(page);
    }
}
