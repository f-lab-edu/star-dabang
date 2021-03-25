package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.office.Office;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface OfficeMapper {

    void insert(Office office);

    void update(Office office);

    Optional<Office> getById(@Param("id") Long id);

    void removeById(@Param("id") Long id);
}
