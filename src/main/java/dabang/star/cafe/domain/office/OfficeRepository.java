package dabang.star.cafe.domain.office;

import dabang.star.cafe.application.data.OfficeData;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;

import java.util.Optional;

public interface OfficeRepository {

    Integer save(Office office);

    Optional<Office> findById(int id);

    void deleteById(int id);

    Optional<OfficeData> findByName(String officeName);

    Page<OfficeData> findAll(Pagination pagination);
}
