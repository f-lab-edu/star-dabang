package dabang.star.cafe.infrastructure.repository;

import dabang.star.cafe.domain.office.Office;
import dabang.star.cafe.domain.office.OfficeData;
import dabang.star.cafe.domain.office.OfficeRepository;
import dabang.star.cafe.infrastructure.mapper.OfficeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MybatisOfficeRepository implements OfficeRepository {

    private final OfficeMapper officeMapper;

    @Override
    public Integer save(Office office) {

        if (office.getId() == null) {
            officeMapper.insert(office);
        } else {
            officeMapper.update(office);
        }

        return office.getId();
    }

    @Override
    public Optional<Office> findById(int id) {
        return officeMapper.getById(id);
    }

    @Override
    public void deleteById(int id) {
        officeMapper.removeById(id);
    }

    @Override
    public Optional<OfficeData> findByName(String officeName) {
        return officeMapper.getByName(officeName);
    }
}
