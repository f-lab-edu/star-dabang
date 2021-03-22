package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.option.Option;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OptionMapper {

    void insert(Option option);

    void update(Option option);

    List<Option> selectAllOption();

}
