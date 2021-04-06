package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.domain.category.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface CategoryMapper {

    void insert(Category category);

    void update(Category category);

    boolean exists(String name);

    Optional<Category> getById(int id);

}
