package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.office.OfficeData;
import dabang.star.cafe.utils.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OfficeReadService {

    List<OfficeData> findOffices(@Param("page") Page page);
}
