package dabang.star.cafe.domain.admin;

import dabang.star.cafe.domain.office.OfficeData;
import dabang.star.cafe.utils.Page;

import java.math.BigDecimal;
import java.util.List;

public interface OfficeAdminService {

    OfficeData createOffice(String name, String address, BigDecimal latitude, BigDecimal longitude);

    void deleteOffice(int officeId);

    void updateOffice(int id, String name, String address, BigDecimal latitude, BigDecimal longitude);

    List<OfficeData> findOffices(Page page);
}
