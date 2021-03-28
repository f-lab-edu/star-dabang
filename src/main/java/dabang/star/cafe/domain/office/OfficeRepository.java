package dabang.star.cafe.domain.office;

import java.util.Optional;

public interface OfficeRepository {

    Integer save(Office office);

    Optional<Office> findById(int id);

    void deleteById(int id);

    Optional<OfficeData> findByName(String officeName);
}
