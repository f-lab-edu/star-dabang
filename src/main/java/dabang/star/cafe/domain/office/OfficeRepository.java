package dabang.star.cafe.domain.office;

import dabang.star.cafe.application.data.OfficeData;
import dabang.star.cafe.application.data.OfficeSearchData;
import dabang.star.cafe.utils.page.Page;
import dabang.star.cafe.utils.page.Pagination;

import java.util.List;
import java.util.Optional;

public interface OfficeRepository {

    Integer save(Office office);

    Optional<Office> findById(int id);

    void deleteById(int id);

    Optional<OfficeData> findByName(String officeName);

    Page<OfficeData> findAll(Pagination pagination);

    List<OfficeSearchData> findNearByLineString(Location curLoc, String lineString);
}
