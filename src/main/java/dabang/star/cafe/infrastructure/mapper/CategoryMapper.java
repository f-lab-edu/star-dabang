package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.category.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface CategoryMapper {

    void insert(Category category);

    void update(Category category);

    Optional<Category> getById(int id);

}
