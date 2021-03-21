package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.option.Option;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OptionMapper {

    void insert(Option option);

    void update(Option option);
    
}
