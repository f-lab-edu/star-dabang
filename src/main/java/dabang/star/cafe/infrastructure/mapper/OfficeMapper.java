package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.application.data.OfficeSearchData;
import dabang.star.cafe.domain.office.Location;
import dabang.star.cafe.domain.office.Office;
import dabang.star.cafe.application.data.OfficeData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OfficeMapper {

    void insert(Office office);

    void update(Office office);

    Optional<Office> getById(@Param("id") Integer id);

    void removeById(@Param("id") Integer id);

    Optional<OfficeData> getByName(String officeName);

    int getCountAll();

    List<OfficeData> getByPagination(int limit, int offset);

    List<OfficeSearchData> getByLineString(Location curLoc, String lineString);
}
