package dabang.star.cafe.domain.admin;

import dabang.star.cafe.domain.office.OfficeData;

import java.math.BigDecimal;

public interface OfficeAdminService {

    OfficeData createOffice(String name, String address, BigDecimal latitude, BigDecimal longitude);

    void deleteOffice(Long officeId);

    void updateOffice(Long id, String name, String address, BigDecimal latitude, BigDecimal longitude);

}
