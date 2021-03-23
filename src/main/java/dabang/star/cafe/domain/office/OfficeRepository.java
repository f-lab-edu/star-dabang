package dabang.star.cafe.domain.office;

import java.util.Optional;

public interface OfficeRepository {

    Long save(Office office);

    Optional<Office> findById(long id);

    void deleteById(long id);
}
