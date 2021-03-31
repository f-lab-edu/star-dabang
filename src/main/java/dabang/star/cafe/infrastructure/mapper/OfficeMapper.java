package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.office.Office;
import dabang.star.cafe.domain.office.OfficeData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface OfficeMapper {

    void insert(Office office);

    void update(Office office);

    Optional<Office> getById(@Param("id") Integer id);

    void removeById(@Param("id") Integer id);

    Optional<OfficeData> getByName(String officeName);
}
