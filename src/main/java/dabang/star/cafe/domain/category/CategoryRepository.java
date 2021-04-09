package dabang.star.cafe.domain.category;

import java.util.Optional;

public interface CategoryRepository {

    Integer save(Category category);

    Optional<Category> findById(int id);

}
