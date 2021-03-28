package dabang.star.cafe.domain.admin;

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
public class OfficeAdminServiceImpl implements OfficeAdminService {

    private final OfficeRepository officeRepository;

    private final OfficeReadService officeReadService;

    @Override
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

    @Override
    public void deleteOffice(Integer officeId) {
        officeRepository.deleteById(officeId);
    }

    @Override
    public void updateOffice(Integer id, String name, String address, BigDecimal latitude, BigDecimal longitude) {

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

    @Override
    public List<OfficeData> findOffices(Page page) {
        return officeReadService.findOffices(page);
    }
}
