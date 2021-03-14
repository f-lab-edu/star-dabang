package dabang.star.cafe.domain.office;

import java.util.Optional;

public interface OfficeRepository {

    Long save(Office office);

    Optional<Office> findOfficeById(long id);

    void deleteById(long id);
}
