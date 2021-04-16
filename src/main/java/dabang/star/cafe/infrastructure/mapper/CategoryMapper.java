package dabang.star.cafe.infrastructure.mapper;

import dabang.star.cafe.application.data.CategoryData;
import dabang.star.cafe.domain.category.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CategoryMapper {

    void insert(Category category);

    void update(Category category);

    Optional<Category> getById(int id);

    List<CategoryData> selectAll(int limit, int offset);

    int getAllCount();

    int removeById(int id);

    List<CategoryData> getAllByType(String type);
    
}
