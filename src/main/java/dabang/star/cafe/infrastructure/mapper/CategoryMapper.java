package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.category.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    void insert(Category category);

    void update(Category category);

}
