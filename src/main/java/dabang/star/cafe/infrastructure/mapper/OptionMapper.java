package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.option.Option;
import dabang.star.cafe.utils.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OptionMapper {

    void insert(Option option);

    void update(Option option);

    List<Option> selectAllOption(@Param("page") Page page);

    Optional<Option> getById(@Param("id") int id);

    int removeById(@Param("id") int id);

}
